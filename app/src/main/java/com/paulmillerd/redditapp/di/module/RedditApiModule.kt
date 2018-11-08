package com.paulmillerd.redditapp.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paulmillerd.redditapp.api.RedditService
import com.paulmillerd.redditapp.api.deserializers.ListingDataDeserializer
import com.paulmillerd.redditapp.api.responseModels.Listing.Data
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RedditApiModule {

    @Provides @Singleton fun providesGson(): Gson =
            GsonBuilder()
                    .registerTypeAdapter(Data::class.java, ListingDataDeserializer())
                    .create()

    @Provides @Singleton fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build()

    @Provides @Singleton fun provideRedditService(gson: Gson, okHttpClient: OkHttpClient): RedditService =
            Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(RedditService::class.java)

}
