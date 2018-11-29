package com.paulmillerd.redditapp.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
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
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.paulmillerd.redditapp.R
import kotlinx.android.synthetic.main.fragment_link.*


class LinkFragment: Fragment() {

    companion object {
        const val URL = "URL"
        const val DASH_URL_SUFFIX = "/DASHPlaylist.mpd"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_link, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val player = ExoPlayerFactory.newSimpleInstance(context)
        player_view.player = player
        player_view.visibility = VISIBLE
        val dataSourceFactory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context?.getString(R.string.app_name)))
        val videoSource = DashMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse("$url$DASH_URL_SUFFIX"))
        player.playWhenReady = true
        player.prepare(videoSource)
    }

}