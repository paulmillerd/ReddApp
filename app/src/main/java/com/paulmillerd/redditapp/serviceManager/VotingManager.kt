package com.paulmillerd.redditapp.serviceManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.di.RedditServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VotingManager @Inject constructor(private val serviceProvider: RedditServiceProvider) {

    fun upvote(thingId: String): LiveData<Boolean> {
        val success = MutableLiveData<Boolean>()
        serviceProvider.redditService.castVote(1, thingId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                success.postValue(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
                success.postValue(false)
            }
        })
        return success
    }

    fun downvote(thingId: String): LiveData<Boolean> {
        val success = MutableLiveData<Boolean>()
        serviceProvider.redditService.castVote(-1, thingId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                success.postValue(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
                success.postValue(false)
            }
        })
        return success
    }

    fun resetVote(thingId: String): LiveData<Boolean> {
        val success = MutableLiveData<Boolean>()
        serviceProvider.redditService.castVote(0, thingId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                success.postValue(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
                success.postValue(false)
            }
        })
        return success
    }

}