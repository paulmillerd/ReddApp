package com.paulmillerd.redditapp.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.toMagnitudeString
import kotlinx.android.synthetic.main.comments_item.view.*
import ru.noties.markwon.Markwon

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder =
                CommentViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.comments_item, parent, false))
    }

    fun bindView(item: Thing?) {
        with(itemView) {
            depth_spacer.layoutParams.width = 30 * (item?.data?.depth ?: 0)
            left_border.visibility = if ((item?.data?.depth ?: 0) > 0) VISIBLE else GONE
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