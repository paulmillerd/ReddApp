package com.paulmillerd.redditapp.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.paulmillerd.redditapp.repository.AccountRepository

class AccountViewModel: ViewModel() {

    private lateinit var accountRepository: AccountRepository

    private val _loggedIn = MutableLiveData<Boolean>()
    val accountInfo = Transformations.switchMap(_loggedIn) {
        accountRepository.getAccountInfo()
    }

    fun init(accountRepository: AccountRepository) {
        this.accountRepository = accountRepository
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        if (isLoggedIn != _loggedIn.value)
            _loggedIn.postValue(isLoggedIn)
    }

}