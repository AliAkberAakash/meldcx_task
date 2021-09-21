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

    /**
     * Setup the [WebView] configurations
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun  setupWebView(){

        // Enable JavaScript
        val settings = binding.webView.settings
        settings.javaScriptEnabled = true

        // Setup WebViewClient to load url inside the app
        // and not in default browser
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.loader.makeItGone()
                super.onPageFinished(view, url)
            }

        }
    }

    private fun setListeners(){
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