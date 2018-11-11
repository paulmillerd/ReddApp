package com.paulmillerd.redditapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.hideKeyboard
import com.paulmillerd.redditapp.ui.listing.ListingFragment
import com.paulmillerd.redditapp.ui.listing.ListingFragment.Companion.SORT_ORDER
import com.paulmillerd.redditapp.ui.listing.ListingFragment.Companion.SUBREDDIT
import com.paulmillerd.redditapp.ui.listingPicker.ListingPickerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        ListingPickerFragment.ListingPickerCallback,
        ListingFragment.ListingFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceListingFragment("", null, false)
    }

    override fun onSubredditEntered(subreddit: String) {
        hideKeyboard(this)
        onBackPressed()
        replaceListingFragment(subreddit, null, true)
    }

    override fun onToolbarTapped() {
        supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, ListingPickerFragment().also {
                    it.callback = this
                })
                .addToBackStack(null)
                .commit()
    }

    private fun replaceListingFragment(subreddit: String, sortOrder: SortOrder?, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, ListingFragment().also { fragment ->
                    fragment.arguments = Bundle().also { bundle ->
                        bundle.putString(SUBREDDIT, subreddit)
                        if (sortOrder != null) bundle.putSerializable(SORT_ORDER, sortOrder)
                    }
                    fragment.callback = this
                })
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
        content_frame.requestFocus()
    }

}
