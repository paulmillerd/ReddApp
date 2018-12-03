package com.paulmillerd.redditapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.di.RedditServiceProvider
import com.paulmillerd.redditapp.redditApi.responseModels.account.AccountResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(private val serviceProvider: RedditServiceProvider) {

    fun getAccountInfo(): LiveData<AccountResponse> {
        val data = MutableLiveData<AccountResponse>()

        serviceProvider.redditService.getAccountInfo().enqueue(object : Callback<AccountResponse> {

            override fun onResponse(call: Call<AccountResponse>, response: Response<AccountResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return data
    }

}