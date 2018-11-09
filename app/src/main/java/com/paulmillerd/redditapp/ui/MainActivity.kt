package com.paulmillerd.redditapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.hideKeyboard
import com.paulmillerd.redditapp.ui.listingPicker.ListingPickerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListingPickerFragment.ListingPickerCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (listing_picker_fragment as ListingPickerFragment).callback = this
    }

    override fun onSubredditEntered(subreddit: String) {
        hideKeyboard(this)
        (listing_fragment as SubredditInterface).setSubreddit(subreddit)
        (listing_picker_fragment as SubredditInterface).setSubreddit(subreddit)
    }

}
