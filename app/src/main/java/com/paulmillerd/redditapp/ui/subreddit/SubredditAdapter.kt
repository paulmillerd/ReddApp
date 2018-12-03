package com.paulmillerd.redditapp.ui.subreddit

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing
import com.paulmillerd.redditapp.ui.VoteCallback

class SubredditAdapter: PagedListAdapter<Thing, SubredditViewHolder>(ChildrenDiffer()) {

    var fragmentCallback: SubredditFragment.SubredditFragmentCallback? = null
    var voteCallback: VoteCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubredditViewHolder =
            SubredditViewHolder.create(parent, fragmentCallback, voteCallback)

    override fun onBindViewHolder(holder: SubredditViewHolder, position: Int) {
        holder.bindChild(getItem(position))
    }

    class ChildrenDiffer: DiffUtil.ItemCallback<Thing>() {
        override fun areItemsTheSame(p0: Thing, p1: Thing): Boolean =
                p0.data?.id == p1.data?.id

        override fun areContentsTheSame(p0: Thing, p1: Thing): Boolean =
                p0.data?.editedTime == p1.data?.editedTime &&
                        p0.data?.clicked == p1.data?.clicked &&
                        p0.tempScore == p1.tempScore &&
                        p0.tempLikes == p1.tempLikes &&
                        p0.data?.numComments == p1.data?.numComments
    }
}