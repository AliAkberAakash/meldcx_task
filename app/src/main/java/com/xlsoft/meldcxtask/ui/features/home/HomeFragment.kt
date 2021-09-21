package com.xlsoft.meldcxtask.ui.features.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.core.ui.BaseFragment
import com.xlsoft.meldcxtask.databinding.FragmentHomeBinding
import com.xlsoft.meldcxtask.ui.utils.makeItGone
import com.xlsoft.meldcxtask.ui.utils.makeItVisible
import com.xlsoft.meldcxtask.ui.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val _viewModel : HomeViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView()
        setListeners()

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
    }

}