package com.paulmillerd.redditapp.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import kotlinx.android.synthetic.main.selftext_view_holder.view.*

class SelfTextViewHolder(itemView: View): CommentListViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup) =
                SelfTextViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.selftext_view_holder, parent, false))
    }

    override fun bindView(item: CommentsAdapter.CommentOrSelfText) {
        with (itemView) {
            self_text.text = item.selfText
        }
    }
}