package com.paulmillerd.redditapp.redditApi

import android.app.Application
import android.content.Context
import com.paulmillerd.redditapp.*
import okhttp3.Credentials

fun refreshAccessToken(application: Application, redditService: RedditService): Boolean {
    val response = redditService.refreshAccessToken(
            authHeader = getBasicAuthHeader(),
            refreshToken = getRefreshToken(application)
    ).execute()

    return if (response.isSuccessful && response.body() != null &&
            response.body()?.accessToken != null && response.body()?.expiresIn != null) {
        saveAccessToken(application, response.body()?.accessToken!!, response.body()?.expiresIn!!)
        true
    } else false
}

fun getAccessToken(application: Application): String =
        application.getSharedPreferences(ACCESS_TOKEN_FILE, Context.MODE_PRIVATE)
                ?.getString(ACCESS_TOKEN, "") ?: ""

fun isAccessTokenExpired(application: Application): Boolean {
    application.getSharedPreferences(ACCESS_TOKEN_FILE, Context.MODE_PRIVATE)?.apply {
        return (System.currentTimeMillis() / 1000) >= getLong(ACCESS_TOKEN_EXPIRY_TIME, 0)
    }
}

fun getBasicAuthHeader(): String = Credentials.basic(REDDIT_CLIENT_ID, "")

fun saveAccessToken(application: Application, accessToken: String, expiresIn: Int) {
    application.getSharedPreferences(ACCESS_TOKEN_FILE, Context.MODE_PRIVATE)?.apply {
        with (edit()) {
            putString(com.paulmillerd.redditapp.ACCESS_TOKEN, accessToken)
            putLong(com.paulmillerd.redditapp.ACCESS_TOKEN_EXPIRY_TIME, (java.lang.System.currentTimeMillis() / 1000) + expiresIn)
            apply()
        }
    }
}

fun saveRefreshToken(application: Application, refreshToken: String) {
    application.getSharedPreferences(ACCESS_TOKEN_FILE, Context.MODE_PRIVATE)?.apply {
        with (edit()) {
            putString(com.paulmillerd.redditapp.REFRESH_TOKEN, refreshToken)
            apply()
        }
    }
}

fun getRefreshToken(application: Application): String =
        application.getSharedPreferences(ACCESS_TOKEN_FILE, Context.MODE_PRIVATE)
                ?.getString(REFRESH_TOKEN, "") ?: ""