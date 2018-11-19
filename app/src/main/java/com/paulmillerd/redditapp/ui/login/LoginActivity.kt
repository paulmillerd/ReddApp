package com.paulmillerd.redditapp.ui.login

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.repository.AccessTokenRepository
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity: AppCompatActivity() {

    companion object {
        const val STATE = "STATE"
    }

    @Inject
    lateinit var accessTokenRepository: AccessTokenRepository

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        RedditApp.getAppComponent(applicationContext).inject(this)

        progress_bar.indeterminateDrawable.setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimary),
                PorterDuff.Mode.SRC_ATOP
        )

        val data = intent.data

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.init(accessTokenRepository)

        data?.getQueryParameter("code")?.let { code ->
            viewModel.setCode(code)
        }

        viewModel.retrieveTokenResponse.observe(this, Observer { success ->
            if (success) {
                finish()
            } else {
                AlertDialog.Builder(this)
                        .setTitle(R.string.uh_oh)
                        .setMessage(R.string.problem_logging_in)
                        .setCancelable(false)
                        .setNeutralButton(R.string.ok) { _, _ ->
                            finish()
                        }
            }
        })
    }

}