package com.paulmillerd.redditapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.RedditService
import com.paulmillerd.redditapp.api.responseModels.Listing.ListingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListingRepository @Inject constructor(private val redditService: RedditService) {

    fun getListing(subreddit: String?, sortOrder: SortOrder): LiveData<ListingResponse> {
        val data = MutableLiveData<ListingResponse>()

        if (TextUtils.isEmpty(subreddit)) {
            redditService.getFrontPage(sortOrder.pathParam)
        } else {
            redditService.getSubreddit(subreddit!!, sortOrder.pathParam)
        }.enqueue(object : Callback<ListingResponse> {
            override fun onResponse(call: Call<ListingResponse>, response: Response<ListingResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ListingResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }

}