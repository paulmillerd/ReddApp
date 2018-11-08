package com.paulmillerd.redditapp.api

import com.paulmillerd.redditapp.api.responseModels.Listing.ListingResponse
import com.paulmillerd.redditapp.api.responseModels.comments.CommentsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("{sortOrder}.json")
    fun getFrontPage(@Path("sortOrder") sortOrder: String, @Query("after") after: String?): Call<ListingResponse>

    @GET("r/{subreddit}/{sortOrder}.json")
    fun getSubreddit(@Path("subreddit") subreddit: String, @Path("sortOrder") sortOrder: String, @Query("after") after: String?):
            Call<ListingResponse>

    @GET("r/{subreddit}/comments/{id}/{title}/.json?sort={sortOrder}")
    fun getComments(@Path("subreddit") subreddit: String, @Path("id") id: String,
                    @Path("title") title: String, @Path("sortOrder") sortOrder: String): Call<CommentsResponse>

}