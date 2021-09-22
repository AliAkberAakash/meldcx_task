package com.xlsoft.meldcxtask.ui.features.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseFragment
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import com.xlsoft.meldcxtask.databinding.FragmentHomeBinding
import com.xlsoft.meldcxtask.ui.features.shared.SharedViewModel
import com.xlsoft.meldcxtask.ui.utils.makeItGone
import com.xlsoft.meldcxtask.ui.utils.makeItVisible
import com.xlsoft.meldcxtask.ui.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Author: Ali Akber
 * Email: ali852609@gmail.com
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val _viewModel : HomeViewModel by viewModels()
    private val _sharedViewModel : SharedViewModel by navGraphViewModels(R.id.nav_graph)

    override fun getLayoutId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView()
        setListeners()
        setObservers()

    }

    /**
     * Sets observers on LiveData objects
     */
    private fun setObservers(){

        /**
         * Observe selectedHistory field in SharedViewModel
         * The [SharedViewModel] instance is shared by both HomeFragment and
         * HistoryFragment. Since its a shared instance, it retains its value
         * even when we navigate to and from fragments.
         * selectedHistory holds the value of the history selected in the
         * HistoryFragment. When HomeFragment is re-creates, this observer is called
         * again and the webView is loaded with the selected value from historyFragment
         */
        _sharedViewModel.selectedHistory.observe(viewLifecycleOwner){
            binding.urlField.setText(it.url)
            Glide
                .with(requireContext())
                .load(File(it.imagePath))
                .centerCrop()
                .into(binding.loadingImage)
            loadWebView()
        }
    }


    //Setup the [WebView] configurations
    @SuppressLint("SetJavaScriptEnabled")
    private fun  setupWebView(){

        // Enable JavaScript
        val settings = binding.webView.settings
        settings.javaScriptEnabled = true


        binding.webView.webViewClient = object : WebViewClient() {

            // Setup [WebViewClient] to load url inside the app
            // and not in default browser
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            // hide the loader image when webpage finishes loading
            override fun onPageFinished(view: WebView?, url: String?) {
                binding.loadingImage.makeItGone()
                showShortToast(requireContext(), getString(R.string.finished_loading_webpage))
                super.onPageFinished(view, url)
            }

        }
    }

    /**
     * Loads the text from urlField into the webView
     * If the url is empty it shows an error Toast
     */
    private fun loadWebView(){
        val url = binding.urlField.text.toString().trim()
        binding.loadingImage.makeItVisible()
        if(url.isEmpty()){
            showShortToast(requireContext(), getString(R.string.enter_url_prompt))
        }else{
            showShortToast(requireContext(), getString(R.string.loading_webpage))
            binding.webView.loadUrl(url)
        }
    }

    // set listeners
    private fun setListeners(){
        /**
         * On Go button click
         *  if the url field is empty show empty Toast
         *  else load the url into the [WebView]
         */
        binding.goButton.setOnClickListener {
            loadWebView()
        }

        /**
         * Capture the [WebView] as a bitmap
         * Save the image in scoped storage
         * Save the image path in database
         */
        binding.captureButton.setOnClickListener {
            val bitmap = binding.webView.drawToBitmap() // get bitmap from the webView
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream) // convert bitmap to jpg stream
            val byteArray = stream.toByteArray()
            val imagePath = writeBytesAsJPEG(byteArray) // save the jpeg image
            saveHistoryToDB(imagePath, binding.urlField.text.toString())
        }

        binding.historyButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHistoryFragment())
        }

    }

    /**
     * Stores bytesArray as image in
     * scoped storage, and return the saved path
     */
    private fun writeBytesAsJPEG(bytes : ByteArray) : String{
        val path = requireContext().filesDir
        val file = File.createTempFile("my_file_",".jpeg", path)
        val os = FileOutputStream(file)
        os.write(bytes)
        os.close()
        return file.absolutePath
    }

    /**
     * Saves a history object in the database
     */
    private fun saveHistoryToDB(imagePath: String, url : String){
        val currentTime = Calendar.getInstance().time

        val searchHistory = SearchHistory(
          url = url,
          imagePath = imagePath,
          created_at = currentTime.toString(),
        )

        _viewModel.insertSearchHistory(searchHistory)

    }

}