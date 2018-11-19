package com.paulmillerd.redditapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulmillerd.redditapp.LOGIN_DEEP_LINK
import com.paulmillerd.redditapp.api.RedditService
import com.paulmillerd.redditapp.api.getBasicAuthHeader
import com.paulmillerd.redditapp.api.responseModels.accessToken.RetrieveTokenResponse
import com.paulmillerd.redditapp.api.saveAccessToken
import com.paulmillerd.redditapp.api.saveRefreshToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AccessTokenRepository @Inject constructor(
        @Named("redditService") private val redditService: RedditService,
        private val application: Application
) {

    fun retrieveAccessToken(code: String): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        redditService.retrieveAccessToken(
                authHeader = getBasicAuthHeader(),
                code = code,
                redirectUrl = LOGIN_DEEP_LINK
        ).enqueue(object : Callback<RetrieveTokenResponse> {

            override fun onResponse(
                    call: Call<RetrieveTokenResponse>,
                    response: Response<RetrieveTokenResponse>
            ) {
                if (response.isSuccessful && response.body() != null &&
                        response.body()?.accessToken != null && response.body()?.expiresIn != null &&
                        response.body()?.refreshToken != null) {
                    saveAccessToken(application, response.body()?.accessToken!!, response.body()?.expiresIn!!)
                    saveRefreshToken(application, response.body()?.refreshToken!!)
                    data.postValue(true)
                } else {
                    data.postValue(false)
                }
            }

            override fun onFailure(call: Call<RetrieveTokenResponse>, t: Throwable) {
                t.printStackTrace()
                data.postValue(false)
            }

        })

        return data
    }

}