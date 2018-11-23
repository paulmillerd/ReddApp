package com.paulmillerd.redditapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.paulmillerd.redditapp.R
import kotlinx.android.synthetic.main.fragment_web_view.*


class LinkFragment: Fragment() {

    companion object {
        const val URL = "URL"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_web_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(URL)
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            val type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            if (type?.startsWith("image/") == true) {
                showImage(url)
            } else {
                showWebPage(url)
            }
        }
    }

    private fun showImage(url: String?) {
        photo_view.visibility = VISIBLE
        Glide.with(this)
                .load(url)
                .into(photo_view)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showWebPage(url: String?) {
        with (web_view) {
            visibility = VISIBLE
            webViewClient = object : WebViewClient() {
                @Suppress("OverridingDeprecatedMember")
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean = false

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean = false
            }
            with (settings) {
                builtInZoomControls = true
                displayZoomControls = false
                javaScriptEnabled = true
            }
            loadUrl(url)
        }
    }

}