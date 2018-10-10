package com.paulmillerd.redditapp.api

import com.paulmillerd.redditapp.api.responseModels.ListingResponse
import retrofit2.Call
import retrofit2.http.GET

interface RedditService {
    @GET("best.json")
    fun getBest(): Call<ListingResponse>
}