package com.paulmillerd.redditapp.api

import com.paulmillerd.redditapp.api.responseModels.listing.Listing
import com.paulmillerd.redditapp.api.responseModels.listing.MoreCommentsResponse
import com.paulmillerd.redditapp.api.responseModels.subredditAbout.AboutResponse
import com.paulmillerd.redditapp.api.responseModels.subredditAutocomplete.AutocompleteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("{sortOrder}.json?raw_json=1")
    fun getFrontPage(@Path("sortOrder") sortOrder: String,
                     @Query("after") after: String?):
            Call<Listing>

    @GET("r/{subreddit}/{sortOrder}.json?raw_json=1")
    fun getSubreddit(@Path("subreddit") subreddit: String,
                     @Path("sortOrder") sortOrder: String,
                     @Query("after") after: String?):
            Call<Listing>

    @GET("comments/{id}/.json?raw_json=1")
    fun getComments(@Path("id") id: String,
                    @Query("sort") sortOrder: String):
            Call<List<Listing>>

    @GET("r/{subreddit}/about.json?raw_json=1")
    fun getSubredditAbout(@Path("subreddit") subreddit: String): Call<AboutResponse>

    @GET("api/subreddit_autocomplete.json?raw_json=1")
    fun getSubredditAutocomplete(@Query("query") query: String,
                                 @Query("include_over_18") includeOver18: Boolean,
                                 @Query("include_profiles") includeProfiles: Boolean):
            Call<AutocompleteResponse>

    @GET("api/morechildren.json?api_type=json&raw_json=1")
    fun getMoreComments(@Query("link_id") linkId: String,
                        @Query("children") children: String):
            Call<MoreCommentsResponse>

}