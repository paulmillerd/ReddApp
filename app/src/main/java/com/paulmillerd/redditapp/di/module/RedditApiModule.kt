package com.paulmillerd.redditapp.di.module

import com.paulmillerd.redditapp.api.GsonInstances
import com.paulmillerd.redditapp.api.RedditService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RedditApiModule {

    @Provides @Singleton fun providesGsonInstances(): GsonInstances =
            GsonInstances()

    @Provides @Singleton fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build()

    @Provides @Singleton fun provideRedditService(gsonInstances: GsonInstances, okHttpClient: OkHttpClient): RedditService =
            Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gsonInstances.gsonForThingData))
                    .build()
                    .create(RedditService::class.java)

}
