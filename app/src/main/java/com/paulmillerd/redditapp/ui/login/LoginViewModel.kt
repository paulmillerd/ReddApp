package com.paulmillerd.redditapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.paulmillerd.redditapp.repository.AccessTokenRepository

class LoginViewModel: ViewModel() {

    private lateinit var accessTokenRepository: AccessTokenRepository

    private val _code = MutableLiveData<String>()
    val retrieveTokenResponse: LiveData<Boolean> = Transformations.switchMap(_code) {
        accessTokenRepository.retrieveAccessToken(it)
    }

    fun init(accessTokenRepository: AccessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository
    }

    fun setCode(code: String) {
        _code.postValue(code)
    }

}