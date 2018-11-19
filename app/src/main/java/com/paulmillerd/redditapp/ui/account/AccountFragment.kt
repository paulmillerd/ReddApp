package com.paulmillerd.redditapp.ui.account

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.api.getAccessToken
import com.paulmillerd.redditapp.getSaltString
import com.paulmillerd.redditapp.repository.AccountRepository
import com.paulmillerd.redditapp.ui.login.AuthenticateWebViewActivity
import kotlinx.android.synthetic.main.fragment_account.*
import javax.inject.Inject

class AccountFragment: Fragment() {

    @Inject
    lateinit var accountRepository: AccountRepository
    @Inject
    lateinit var application: Application

    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_account, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        login_button.setOnClickListener {
            context?.let { ctx ->
                AuthenticateWebViewActivity.start(ctx, "LOGIN-SCREEN_" + getSaltString())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let { ctx ->
            RedditApp.getAppComponent(ctx).inject(this)
            viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
            viewModel.init(accountRepository)
            viewModel.accountInfo.observe(this, Observer { response ->
                if (response.id != null) {
                    username.text = response.name
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        if (!TextUtils.isEmpty(getAccessToken(application))) {
            logged_in_layout.visibility = VISIBLE
            logged_out_layout.visibility = GONE
            viewModel.setIsLoggedIn(true)
        } else {
            logged_out_layout.visibility = VISIBLE
            logged_in_layout.visibility = GONE
            viewModel.setIsLoggedIn(false)
        }
    }

}