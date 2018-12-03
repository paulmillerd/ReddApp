package com.paulmillerd.redditapp.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.hideKeyboard
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing
import com.paulmillerd.redditapp.ui.post.PostFragment
import com.paulmillerd.redditapp.ui.subreddit.SubredditFragment
import com.paulmillerd.redditapp.ui.subredditPicker.SubredditPickerFragment
import kotlinx.android.synthetic.main.fragment_browse.*

class BrowseFragment: Fragment(),
        SubredditPickerFragment.SubredditPickerCallback,
        SubredditFragment.SubredditFragmentCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_browse, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceSubredditFragment("", null, false)
    }

    override fun onSubredditEntered(subreddit: String) {
        activity?.let { hideKeyboard(it) }
        activity?.onBackPressed()
        replaceSubredditFragment(subreddit, null, true)
    }

    override fun onToolbarTapped() {
        childFragmentManager.beginTransaction()
                .add(R.id.browse_content_frame, SubredditPickerFragment().also {
                    it.callback = this
                })
                .addToBackStack(null)
                .commit()
    }

    override fun onPostTapped(post: Thing) {
        childFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_slide_up, android.R.animator.fade_out,
                        android.R.animator.fade_out, R.anim.exit_slide_down)
                .add(R.id.browse_content_frame, PostFragment().also { fragment ->
                    fragment.arguments = Bundle().also { bundle ->
                        bundle.putSerializable(PostFragment.POST_DATA, post)
                    }
                })
                .addToBackStack(null)
                .commit()
    }

    private fun replaceSubredditFragment(subreddit: String, sortOrder: SortOrder?, addToBackStack: Boolean) {
        val transaction = childFragmentManager.beginTransaction()
                .replace(R.id.browse_content_frame, SubredditFragment().also { fragment ->
                    fragment.arguments = Bundle().also { bundle ->
                        bundle.putString(SubredditFragment.SUBREDDIT, subreddit)
                        if (sortOrder != null) bundle.putSerializable(SubredditFragment.SORT_ORDER, sortOrder)
                    }
                    fragment.callback = this
                })
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
        browse_content_frame.requestFocus()
    }

}