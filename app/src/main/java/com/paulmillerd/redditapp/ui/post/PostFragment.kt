package com.paulmillerd.redditapp.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment: Fragment() {

    companion object {
        const val POST_DATA = "POST_DATA"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_post, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postData = arguments?.getSerializable(POST_DATA) as Thing

        context?.let { ctx ->
            view_pager.adapter = PostViewPagerAdapter(childFragmentManager, postData, ctx)
        }

        if (postData.data?.isSelf == false) {
            tabs.visibility = VISIBLE
            tabs.setupWithViewPager(view_pager)
        } else tabs.visibility = GONE
    }

}