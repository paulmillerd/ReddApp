package com.paulmillerd.redditapp.ui.comments

import android.view.ViewGroup
import com.paulmillerd.redditapp.ThingType
import com.paulmillerd.redditapp.api.responseModels.listing.Thing

class CommentsAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<CommentListViewHolder>() {

    companion object {
        const val COMMENT = 0
        const val MORE_COMMENTS = 1
    }

    var commentList = listOf<Thing?>()

    override fun getItemViewType(position: Int): Int =
            if (commentList[position]?.kind == ThingType.COMMENT.prefix) COMMENT
            else MORE_COMMENTS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder =
            if (viewType == COMMENT) CommentViewHolder.create(parent)
            else MoreCommentsViewHolder.create(parent)

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: CommentListViewHolder, position: Int) =
        holder.bindView(commentList[position])

}