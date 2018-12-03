package com.paulmillerd.redditapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.di.RedditServiceProvider
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Listing
import com.paulmillerd.redditapp.redditApi.responseModels.listing.MoreCommentsResponse
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepository @Inject constructor(private val serviceProvider: RedditServiceProvider) {

    fun getComments(id: String, sortOrder: SortOrder): LiveData<List<Listing>> {
        val data = MutableLiveData<List<Listing>>()

        serviceProvider.redditService.getComments(id, sortOrder.pathParam).enqueue(object : Callback<List<Listing>> {
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

    fun getMoreComments(linkTypePrefix: String, linkId: String, children: List<String?>, parentId: String): LiveData<MoreCommentsWithParentId> {
        val data = MutableLiveData<MoreCommentsWithParentId>()

        var childrenString = ""
        repeat(children.size) { iteration ->
            childrenString += children[iteration]
            if (iteration != children.size - 1) childrenString += ","
        }

        serviceProvider.redditService.getMoreComments(String.format("%s_%s", linkTypePrefix, linkId), childrenString)
                .enqueue(object : Callback<MoreCommentsResponse> {
                    override fun onResponse(call: Call<MoreCommentsResponse>, response: Response<MoreCommentsResponse>) {
                        if (response.isSuccessful) {
                            data.postValue(MoreCommentsWithParentId(
                                    moreComments = response.body()?.json?.data?.things ?: listOf(),
                                    parentId = parentId
                            ))
                        }
                    }

                    override fun onFailure(call: Call<MoreCommentsResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

        return data
    }

    data class MoreCommentsWithParentId(
            val moreComments: List<Thing>,
            val parentId: String
    )

}