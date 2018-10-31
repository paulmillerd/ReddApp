package com.paulmillerd.redditapp.ui.listing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.Listing.ChildrenItem

class ListingAdapter: RecyclerView.Adapter<ListingViewHolder>() {

    val children = mutableListOf<ChildrenItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder =
            ListingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listing_item_layout, parent, false))

    override fun getItemCount(): Int =
            children.size

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.bindChild(children[position])
    }
}