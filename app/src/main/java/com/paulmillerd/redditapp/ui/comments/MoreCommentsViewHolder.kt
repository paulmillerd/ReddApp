package com.paulmillerd.redditapp.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import kotlinx.android.synthetic.main.more_comments_item.view.*

class MoreCommentsViewHolder(itemView: View, val callback: MoreCommentsVhCallback) : CommentListViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup, callback: MoreCommentsVhCallback) =
                MoreCommentsViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.more_comments_item, parent, false),
                        callback)
    }

    override fun bindView(item: CommentsAdapter.CommentOrSelfText) {
        with (itemView) {
            progress_bar.visibility = GONE
            depth_lines.removeAllViews()
            repeat(item.comment?.data?.depth ?: 0) { depth_lines.addView(DepthLine(context)) }
            more_text.text =
                    when {
                        item.comment?.data?.count == null -> ""
                        item.comment.data.count > 1 -> String.format(
                                context.getString(R.string.more_replies, item.comment.data.count)
                        )
                        else -> context.getString(R.string.more_replies_singular)
                    }
            setOnClickListener { item.comment?.let { comment ->
                progress_bar.visibility = VISIBLE
                callback.onMoreCommentsTapped(comment)
            } }
        }
    }

    interface MoreCommentsVhCallback {
        fun onMoreCommentsTapped(moreCommentsItem: Thing)
    }

}