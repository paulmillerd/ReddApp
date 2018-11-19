package com.paulmillerd.redditapp.di.module

import android.app.Application
import com.paulmillerd.redditapp.BASE_REDDIT_URL
import com.paulmillerd.redditapp.OAUTH_BASE_REDDIT_URL
import com.paulmillerd.redditapp.api.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RedditApiModule(val application: Application) {

    @Provides @Singleton fun providesApplication(): Application = application

    @Provides @Singleton fun providesGsonInstances(): GsonInstances =
            GsonInstances()

    @Provides @Singleton @Named("okHttpClient")
    fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .build()

    @Provides @Singleton @Named("redditService")
    fun providesRedditService(gsonInstances: GsonInstances, @Named("okHttpClient") okHttpClient: OkHttpClient): RedditService =
            Retrofit.Builder()
                    .baseUrl(BASE_REDDIT_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gsonInstances.gsonForThingData))
                    .build()
                    .create(RedditService::class.java)

    @Provides @Singleton @Named("oauthOkHttpClient")
    fun providesOauthOkHttpClient(@Named("redditService") redditService: RedditService): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        if (isAccessTokenExpired(application)) {
                            refreshAccessToken(application, redditService)
                        }
                        chain.proceed(chain.request().newBuilder()
                                .addHeader("Authorization", "bearer " + getAccessToken(application))
                                .build())
                    }
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .build()

    @Provides @Singleton @Named("oauthRedditService")
    fun providesOauthRedditService(gsonInstances: GsonInstances, @Named("oauthOkHttpClient") oauthOkHttpClient: OkHttpClient): RedditService =
            Retrofit.Builder()
                    .baseUrl(OAUTH_BASE_REDDIT_URL)
                    .client(oauthOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gsonInstances.gsonForThingData))
                    .build()
                    .create(RedditService::class.java)

}
