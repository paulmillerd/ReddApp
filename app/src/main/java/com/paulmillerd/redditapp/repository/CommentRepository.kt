package com.paulmillerd.redditapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.RedditService
import com.paulmillerd.redditapp.api.responseModels.listing.Listing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepository @Inject constructor(private val redditService: RedditService) {

    fun getComments(id: String, sortOrder: SortOrder): LiveData<List<Listing>> {
        val data = MutableLiveData<List<Listing>>()

        redditService.getComments(id, sortOrder.pathParam).enqueue(object : Callback<List<Listing>> {
            override fun onResponse(call: Call<List<Listing>>, response: Response<List<Listing>>) {
                if (response.isSuccessful && response.body() != null)
                    data.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Listing>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }

}