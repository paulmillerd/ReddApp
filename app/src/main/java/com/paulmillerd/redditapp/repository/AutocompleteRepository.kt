package com.paulmillerd.redditapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.api.responseModels.subredditAutocomplete.AutocompleteResponse
import com.paulmillerd.redditapp.di.RedditServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AutocompleteRepository @Inject constructor(private val serviceProvider: RedditServiceProvider) {

    fun getSubredditAutocomplete(query: String, includeOver18: Boolean = true, includeProfiles: Boolean = false):
            LiveData<AutocompleteResponse> {
        val data = MutableLiveData<AutocompleteResponse>()

        serviceProvider.redditService.getSubredditAutocomplete(query, includeOver18 = includeOver18, includeProfiles = includeProfiles)
                .enqueue(object : Callback<AutocompleteResponse> {
                    override fun onResponse(call: Call<AutocompleteResponse>, response: Response<AutocompleteResponse>) {
                        if (response.isSuccessful && response.body() != null) {
                            data.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<AutocompleteResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

        return data
    }

}