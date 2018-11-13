package com.paulmillerd.redditapp.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.toMagnitudeString
import kotlinx.android.synthetic.main.comment_item.view.*
import ru.noties.markwon.Markwon

class CommentViewHolder(itemView: View) : CommentListViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup) =
                CommentViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.comment_item, parent, false))
    }

    override fun bindView(item: Thing?) {
        with(itemView) {
            depth_lines.removeAllViews()
            repeat(item?.data?.depth ?: 0) { depth_lines.addView(DepthLine(context)) }
            username.text = item?.data?.author ?: ""
            score.text =
                    if (item?.data?.score_hidden == true)
                        context.getString(R.string.score_hidden)
                    else
                        item?.data?.score?.toMagnitudeString(context) ?: ""
            Markwon.setMarkdown(comment_body, item?.data?.body ?: "")
        }
    }

}