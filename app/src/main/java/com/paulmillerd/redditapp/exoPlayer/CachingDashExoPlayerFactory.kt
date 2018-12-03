package com.paulmillerd.redditapp.exoPlayer

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.exoplayer2.util.Util
import com.paulmillerd.redditapp.R

class CachingDashExoPlayerFactory {

    companion object {
        fun newInstance(context: Context?, dashUrl: String, cache: SimpleCache): ExoPlayer {
            val player = ExoPlayerFactory.newSimpleInstance(context)
            val dataSourceFactory = DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, context?.getString(R.string.app_name)))
            val cacheDataSourceFactory = CacheDataSourceFactory(cache, dataSourceFactory)
            val dashChunkSource = DefaultDashChunkSource.Factory(cacheDataSourceFactory)
            val videoSource = DashMediaSource.Factory(dashChunkSource, cacheDataSourceFactory)
                    .createMediaSource(Uri.parse(dashUrl))

            player.playWhenReady = true
            player.repeatMode = Player.REPEAT_MODE_ALL
            player.prepare(videoSource)

            return player
        }
    }

}