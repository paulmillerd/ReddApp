package com.paulmillerd.redditapp.ui.comments

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.paulmillerd.redditapp.COMMENT_COLORS
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.getAgeString
import com.paulmillerd.redditapp.toMagnitudeString
import kotlinx.android.synthetic.main.comment_item.view.*
import ru.noties.markwon.Markwon
import kotlin.math.min

class CommentViewHolder(itemView: View) : CommentListViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup) =
                CommentViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.comment_item, parent, false))
    }

    override fun bindView(item: CommentsAdapter.CommentOrSelfText) {
        with(itemView) {
            depth_lines.removeAllViews()
            repeat(item.comment?.data?.depth ?: 0) { depth_lines.addView(DepthLine(context)) }
            comment_background.background.setColorFilter(
                    ContextCompat.getColor(
                            context,
                            COMMENT_COLORS[min(item.comment?.data?.depth ?: 0, COMMENT_COLORS.size - 1)]
                    ),
                    PorterDuff.Mode.SRC_ATOP
            )
            username.text = item.comment?.data?.author ?: ""
            score.text =
                    when {
                        item.comment?.data?.score_hidden == true -> context.getString(R.string.score_hidden)
                        item.comment?.data?.score == 1 -> context.getString(R.string.point_singular)
                        item.comment?.data?.score != null ->
                            String.format(context.getString(R.string.points),
                                    item.comment.data.score.toMagnitudeString(context))
                        else -> ""
                    }
            age.text = item.comment?.data?.createdUtc?.let { getAgeString(it, context) } ?: ""
            Markwon.setMarkdown(comment_body, item.comment?.data?.body ?: "")
        }
    }

}