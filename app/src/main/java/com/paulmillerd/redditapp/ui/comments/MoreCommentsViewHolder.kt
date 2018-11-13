package com.paulmillerd.redditapp.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import kotlinx.android.synthetic.main.more_comments_item.view.*

class MoreCommentsViewHolder(itemView: View) : CommentListViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup) =
                MoreCommentsViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.more_comments_item, parent, false))
    }

    override fun bindView(item: Thing?) {
        with (itemView) {
            depth_lines.removeAllViews()
            repeat(item?.data?.depth ?: 0) { depth_lines.addView(DepthLine(context)) }
            more_text.text =
                    when {
                        item?.data?.count == null -> ""
                        item.data.count > 1 -> String.format(
                                context.getString(R.string.more_replies, item.data.count)
                        )
                        else -> context.getString(R.string.more_replies_singular)
                    }
        }
    }

}