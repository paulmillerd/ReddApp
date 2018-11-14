package com.paulmillerd.redditapp.ui.comments

import android.view.View
import com.paulmillerd.redditapp.api.responseModels.listing.Thing

abstract class CommentListViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    abstract fun bindView(item: Thing?)

}