package com.paulmillerd.redditapp.ui.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.paulmillerd.redditapp.api.RedditService
import com.paulmillerd.redditapp.api.responseModels.ListingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingViewModel: ViewModel() {

    private lateinit var redditService: RedditService

    fun init(redditService: RedditService) {
        this.redditService = redditService
    }

    fun getBestListing(): LiveData<ListingResponse> {
        val liveData = MutableLiveData<ListingResponse>()
        redditService.getBest().enqueue(object : Callback<ListingResponse> {
            override fun onFailure(call: Call<ListingResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ListingResponse>, response: Response<ListingResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    liveData.postValue(response.body())
                }
            }
        })
        return liveData
    }

}