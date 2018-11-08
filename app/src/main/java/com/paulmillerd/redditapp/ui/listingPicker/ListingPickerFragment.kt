package com.paulmillerd.redditapp.ui.listingPicker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import com.paulmillerd.redditapp.R
import kotlinx.android.synthetic.main.fragment_listing_picker.*

class ListingPickerFragment: Fragment() {

    var callback: ListingPickerCallback? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listing_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subreddit_edit_text.setOnEditorActionListener { v, actionId, event ->
            if (actionId == IME_ACTION_DONE) {
                callback?.onSubredditEntered(v.text.toString())
                v.clearFocus()
                true
            } else false
        }
    }

    interface ListingPickerCallback {
        fun onSubredditEntered(subreddit: String)
    }

}