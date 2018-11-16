package com.paulmillerd.redditapp.ui.comments

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.paulmillerd.redditapp.COMMENT_COLORS
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
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

    override fun bindView(item: Thing?) {
        with(itemView) {
            depth_lines.removeAllViews()
            repeat(item?.data?.depth ?: 0) { depth_lines.addView(DepthLine(context)) }
            comment_background.background.setColorFilter(
                    ContextCompat.getColor(
                            context,
                            COMMENT_COLORS[min(item?.data?.depth ?: 0, COMMENT_COLORS.size - 1)]
                    ),
                    PorterDuff.Mode.SRC_ATOP
            )
            username.text = item?.data?.author ?: ""
            score.text =
                    when {
                        item?.data?.score_hidden == true -> context.getString(R.string.score_hidden)
                        item?.data?.score == 1 -> context.getString(R.string.point_singular)
                        item?.data?.score != null ->
                            String.format(context.getString(R.string.points),
                                    item.data.score.toMagnitudeString(context))
                        else -> ""
                    }
            age.text = item?.data?.createdUtc?.let { getAgeString(it, context) } ?: ""
            Markwon.setMarkdown(comment_body, item?.data?.body ?: "")
        }
    }

}