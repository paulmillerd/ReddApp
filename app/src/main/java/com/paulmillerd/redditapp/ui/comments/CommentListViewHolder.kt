package com.paulmillerd.redditapp.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.View
import com.paulmillerd.redditapp.api.responseModels.listing.Thing

abstract class CommentListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    abstract fun bindView(item: Thing?)

}