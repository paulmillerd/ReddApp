package com.paulmillerd.redditapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import com.paulmillerd.redditapp.ACCESS_TOKEN
import com.paulmillerd.redditapp.ACCESS_TOKEN_FILE
import com.paulmillerd.redditapp.api.RedditService
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class RedditServiceProvider @Inject constructor(
        private val application: Application,
        @Named("redditService") private val _redditService: RedditService,
        @Named("oauthRedditService") private val _oauthRedditService: RedditService
) {

    val redditService get() =
        if (application.getSharedPreferences(ACCESS_TOKEN_FILE, MODE_PRIVATE)
                    .contains(ACCESS_TOKEN)) _oauthRedditService
        else _redditService

}