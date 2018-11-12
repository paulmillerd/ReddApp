package com.paulmillerd.redditapp.api

import com.paulmillerd.redditapp.api.responseModels.listing.Listing
import com.paulmillerd.redditapp.api.responseModels.subredditAbout.AboutResponse
import com.paulmillerd.redditapp.api.responseModels.subredditAutocomplete.AutocompleteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("{sortOrder}.json")
    fun getFrontPage(@Path("sortOrder") sortOrder: String,
                     @Query("after") after: String?):
            Call<Listing>

    @GET("r/{subreddit}/{sortOrder}.json")
    fun getSubreddit(@Path("subreddit") subreddit: String,
                     @Path("sortOrder") sortOrder: String,
                     @Query("after") after: String?):
            Call<Listing>

    @GET("comments/{id}/.json")
    fun getComments(@Path("id") id: String,
                    @Query("sort") sortOrder: String):
            Call<List<Listing>>

    @GET("r/{subreddit}/about.json")
    fun getSubredditAbout(@Path("subreddit") subreddit: String): Call<AboutResponse>

    @GET("api/subreddit_autocomplete.json")
    fun getSubredditAutocomplete(@Query("query") query: String,
                                 @Query("include_over_18") includeOver18: Boolean,
                                 @Query("include_profiles") includeProfiles: Boolean):
            Call<AutocompleteResponse>

}