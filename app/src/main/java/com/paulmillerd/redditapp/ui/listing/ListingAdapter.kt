package com.paulmillerd.redditapp.ui.listing

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.Listing.ChildrenItem

class ListingAdapter: PagedListAdapter<ChildrenItem, ListingViewHolder>(ChildrenDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder =
            ListingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listing_item, parent, false))

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.bindChild(getItem(position))
    }

    class ChildrenDiffer: DiffUtil.ItemCallback<ChildrenItem>() {
        override fun areItemsTheSame(p0: ChildrenItem, p1: ChildrenItem): Boolean =
                p0.data?.id == p1.data?.id

        override fun areContentsTheSame(p0: ChildrenItem, p1: ChildrenItem): Boolean =
                p0.data?.editedTime == p1.data?.editedTime &&
                        p0.data?.clicked == p1.data?.clicked &&
                        p0.data?.score == p1.data?.score &&
                        p0.data?.numComments == p1.data?.numComments
    }
}