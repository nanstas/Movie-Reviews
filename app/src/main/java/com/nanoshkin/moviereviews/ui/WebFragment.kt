package com.nanoshkin.moviereviews.ui

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nanoshkin.moviereviews.R
import com.nanoshkin.moviereviews.databinding.FragmentWebBinding

class WebFragment : Fragment(R.layout.fragment_web) {

    private val binding by viewBinding(FragmentWebBinding::bind)

    private val webUrl: String by lazy {
        val args by navArgs<WebFragmentArgs>()
        args.webUrlArg
    }

    companion object {
        const val MAX_PROGRESS = 100
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()
        setWebClient()
        loadUrl(webUrl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        with(binding.webView) {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.domStorageEnabled = true

            webViewClient = object : WebViewClient() {
                @SuppressLint("WebViewClientOnReceivedSslError")
                override
                fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    handler?.proceed()
                }
            }
        }
    }

    private fun setWebClient() {
        with(binding) {
            webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(
                    view: WebView,
                    newProgress: Int
                ) {
                    super.onProgressChanged(view, newProgress)
                    progressBar.progress = newProgress
                    if (newProgress < MAX_PROGRESS && progressBar.visibility == ProgressBar.GONE) {
                        progressBar.visibility = ProgressBar.VISIBLE
                    }

                    if (newProgress == MAX_PROGRESS) {
                        progressBar.visibility = ProgressBar.GONE
                    }
                }
            }
        }
    }

    private fun loadUrl(pageUrl: String) {
        binding.webView.loadUrl(pageUrl)
    }
}
