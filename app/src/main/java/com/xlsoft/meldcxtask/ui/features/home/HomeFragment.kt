package com.xlsoft.meldcxtask.ui.features.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
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

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val _viewModel : HomeViewModel by viewModels()
    private val _sharedViewModel : SharedViewModel by navGraphViewModels(R.id.nav_graph)

    override fun getLayoutId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView()
        setListeners()
        _sharedViewModel

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

            // hide the loader when webpage finishes loading
            override fun onPageFinished(view: WebView?, url: String?) {
                binding.loader.makeItGone()
                super.onPageFinished(view, url)
            }

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
            val url = binding.urlField.text.toString().trim()
            if(url.isEmpty()){
                showShortToast(requireContext(), "Please enter a url")
            }else{
                binding.loader.makeItVisible()
                binding.webView.loadUrl(url)
            }
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
     * Function to store bytesArray as image in
     * scoped storage
     */
    private fun writeBytesAsJPEG(bytes : ByteArray) : String{
        val path = requireContext().filesDir
        val file = File.createTempFile("my_file_",".jpeg", path)
        val os = FileOutputStream(file)
        os.write(bytes)
        os.close()
        return file.absolutePath
    }

    private fun saveHistoryToDB(imagePath: String, url : String){

        val tsLong = System.currentTimeMillis()/1000
        val ts = tsLong.toString()

        val searchHistory = SearchHistory(
          url = url,
          imagePath = imagePath,
          time = ts,
        )

        _viewModel.insertSearchHistory(searchHistory)

    }

}