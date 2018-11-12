package com.paulmillerd.redditapp.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import kotlinx.android.synthetic.main.comments_item.view.*

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder =
                CommentViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.comments_item, parent, false))
    }

    fun bindView(item: Thing?) {
        with (itemView) {
            comment_body.text = item?.data?.body ?: ""
        }
    }

}