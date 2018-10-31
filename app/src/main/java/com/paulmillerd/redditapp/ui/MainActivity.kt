package com.paulmillerd.redditapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.ui.listing.ListingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content_view, ListingFragment())
                    .commit()
        }
    }

}
