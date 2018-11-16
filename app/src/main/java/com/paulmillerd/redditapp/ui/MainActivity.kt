package com.paulmillerd.redditapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.hideKeyboard
import com.paulmillerd.redditapp.ui.comments.CommentsFragment
import com.paulmillerd.redditapp.ui.subreddit.SubredditFragment
import com.paulmillerd.redditapp.ui.subreddit.SubredditFragment.Companion.SORT_ORDER
import com.paulmillerd.redditapp.ui.subreddit.SubredditFragment.Companion.SUBREDDIT
import com.paulmillerd.redditapp.ui.subredditPicker.SubredditPickerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        SubredditPickerFragment.SubredditPickerCallback,
        SubredditFragment.SubredditFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceSubredditFragment("", null, false)
    }

    override fun onSubredditEntered(subreddit: String) {
        hideKeyboard(this)
        onBackPressed()
        replaceSubredditFragment(subreddit, null, true)
    }

    override fun onToolbarTapped() {
        supportFragmentManager.beginTransaction()
                .add(R.id.content_frame, SubredditPickerFragment().also {
                    it.callback = this
                })
                .addToBackStack(null)
                .commit()
    }

    override fun onPostTapped(post: Thing) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_slide_up, android.R.animator.fade_out,
                        android.R.animator.fade_out, R.anim.exit_slide_down)
                .add(R.id.content_frame, CommentsFragment().also { fragment ->
                    fragment.arguments = Bundle().also { bundle ->
                        bundle.putSerializable(CommentsFragment.POST_DATA, post)
                    }
                })
                .addToBackStack(null)
                .commit()
    }

    private fun replaceSubredditFragment(subreddit: String, sortOrder: SortOrder?, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, SubredditFragment().also { fragment ->
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
