package com.paulmillerd.redditapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.api.RedditService
import com.paulmillerd.redditapp.api.responseModels.subredditAbout.AboutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubredditAboutRepositiory @Inject constructor(private val redditService: RedditService) {

    fun getSubredditAbout(subreddit: String): LiveData<AboutResponse> {
        val data = MutableLiveData<AboutResponse>()

        redditService.getSubredditAbout(subreddit).enqueue(object : Callback<AboutResponse> {
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