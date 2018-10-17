package com.paulmillerd.redditapp.di.module

import com.paulmillerd.redditapp.api.RedditService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RedditApiModule {

    @Provides @Singleton fun provideRedditService(): RedditService =
            Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/")
                    .build()
                    .create(RedditService::class.java)

}
