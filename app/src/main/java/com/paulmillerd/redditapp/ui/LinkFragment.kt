package com.paulmillerd.redditapp.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
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
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.exoPlayer.CachingDashExoPlayerFactory
import kotlinx.android.synthetic.main.fragment_link.*
import javax.inject.Inject


class LinkFragment: Fragment() {

    @Inject
    lateinit var simpleCache: SimpleCache

    companion object {
        const val URL = "URL"
        const val DASH_URL_SUFFIX = "/DASHPlaylist.mpd"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_link, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { RedditApp.getAppComponent(it).inject(this) }

        val url = arguments?.getString(URL)
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            val type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            when {
                type?.startsWith("image/") == true -> showImage(url)
                url?.startsWith("https://v.redd.it") == true -> showVideoPlayer(url)
                else -> showWebPage(url)
            }
        }
    }

    private fun showImage(url: String?) {
        photo_view.visibility = VISIBLE
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(object: BitmapImageViewTarget(photo_view) {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        Palette.Builder(resource).generate { palette ->
                            palette?.darkMutedSwatch?.rgb?.let { link_frame.setBackgroundColor(it) }
                        }
                    }
                })
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

    private fun showVideoPlayer(url: String?) {
        player_view.player = CachingDashExoPlayerFactory.newInstance(context, "$url$DASH_URL_SUFFIX", simpleCache)
        player_view.visibility = VISIBLE
    }

}