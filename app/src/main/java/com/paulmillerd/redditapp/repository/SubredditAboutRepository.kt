package com.paulmillerd.redditapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.api.responseModels.subredditAbout.AboutResponse
import com.paulmillerd.redditapp.di.RedditServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubredditAboutRepository @Inject constructor(private val serviceProvider: RedditServiceProvider) {

    fun getSubredditAbout(subreddit: String): LiveData<AboutResponse> {
        val data = MutableLiveData<AboutResponse>()

        serviceProvider.redditService.getSubredditAbout(subreddit).enqueue(object : Callback<AboutResponse> {
            override fun onResponse(call: Call<AboutResponse>, response: Response<AboutResponse>) {
                if (response.isSuccessful && response.body() != null)
                    data.postValue(response.body())
            }

            override fun onFailure(call: Call<AboutResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }

}