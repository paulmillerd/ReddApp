package com.paulmillerd.redditapp.ui.login

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.paulmillerd.redditapp.LOGIN_DEEP_LINK
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.REDDIT_CLIENT_ID

class AuthenticateWebViewActivity: AppCompatActivity() {

    companion object {
        const val STATE = "STATE"

        fun start(context: Context, state: String) {
            context.startActivity(Intent(context, AuthenticateWebViewActivity::class.java)
                    .putExtra(AuthenticateWebViewActivity.STATE, state))
        }
    }

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        webView.loadUrl(String.format(
                getString(R.string.authorize_url),
                REDDIT_CLIENT_ID,
                intent.getStringExtra(STATE),
                LOGIN_DEEP_LINK
        ))
        webView.webViewClient = object : WebViewClient() {

            @Suppress("OverridingDeprecatedMember")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean =
                    shouldOverrideUrlLoading(url)

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean =
                    shouldOverrideUrlLoading(request?.url?.toString())

            private fun shouldOverrideUrlLoading(url: String?): Boolean {
                return if (url?.startsWith(LOGIN_DEEP_LINK) == true) {
                    finishAndFollowDeepLink(url)
                    true
                } else {
                    false
                }
            }

        }
        setContentView(webView)
    }

    private fun finishAndFollowDeepLink(url: String) {
        startActivity(Intent(ACTION_VIEW)
                .setData(Uri.parse(url))
                .putExtra(LoginActivity.STATE, intent.getStringExtra(STATE)))
        finish()
    }

}