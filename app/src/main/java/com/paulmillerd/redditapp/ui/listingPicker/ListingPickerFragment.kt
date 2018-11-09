package com.paulmillerd.redditapp.ui.listingPicker

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.repository.SubredditAboutRepositiory
import com.paulmillerd.redditapp.ui.SubredditInterface
import kotlinx.android.synthetic.main.fragment_listing_picker.*
import javax.inject.Inject

class ListingPickerFragment: Fragment(), SubredditInterface {

    @Inject
    lateinit var subredditAboutRepository: SubredditAboutRepositiory

    var callback: ListingPickerCallback? = null
    private lateinit var viewModel: ListingPickerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listing_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subreddit_edit_text.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == IME_ACTION_DONE) {
                callback?.onSubredditEntered(v.text.toString())
                v.clearFocus()
                true
            } else false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            RedditApp.getAppComponent(it).inject(this)
            viewModel = ViewModelProviders.of(this).get(ListingPickerViewModel::class.java)
            viewModel.init(subredditAboutRepository)
            viewModel.subredditAbout.observe(this, Observer {
                subreddit_edit_text.setText(it?.data?.displayNamePrefixed)
            })
        }
    }

    override fun setSubreddit(newSubreddit: String) {
        viewModel.setSubreddit(newSubreddit)
    }

    interface ListingPickerCallback {
        fun onSubredditEntered(subreddit: String)
    }

}