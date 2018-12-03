package com.paulmillerd.redditapp.di.module

import android.content.Context
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class ExoPlayerModule(val context: Context) {

    @Provides @Singleton fun providesContext() = context

    @Provides @Singleton
    fun providesSimpleCache(context: Context) =
            SimpleCache(
                File(context.cacheDir, "media"),
                LeastRecentlyUsedCacheEvictor(10 * 1024 * 1024)
            )

}