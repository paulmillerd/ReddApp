package com.paulmillerd.redditapp.ui.comments

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.paulmillerd.redditapp.ThingType
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing

class CommentsAdapter(val callback: MoreCommentsViewHolder.MoreCommentsVhCallback):
        ListAdapter<CommentsAdapter.CommentOrSelfText, CommentListViewHolder>(CommentDiffer()) {

    companion object {
        const val COMMENT = 0
        const val MORE_COMMENTS = 1
        const val SELF_TEXT = 2
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when {
            item.comment?.kind == ThingType.COMMENT.prefix -> COMMENT
            item.comment?.kind == ThingType.MORE.prefix -> MORE_COMMENTS
            item.selfText != null -> SELF_TEXT
            else -> COMMENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder =
            when (viewType) {
                COMMENT -> CommentViewHolder.create(parent)
                MORE_COMMENTS -> MoreCommentsViewHolder.create(parent, callback)
                SELF_TEXT -> SelfTextViewHolder.create(parent)
                else -> CommentViewHolder.create(parent)
            }

    override fun onBindViewHolder(holder: CommentListViewHolder, position: Int) =
        holder.bindView(getItem(position))

    class CommentDiffer: DiffUtil.ItemCallback<CommentOrSelfText>() {
        override fun areItemsTheSame(oldItem: CommentOrSelfText, newItem: CommentOrSelfText): Boolean =
                oldItem.selfText == newItem.selfText &&
                        oldItem.comment?.kind == newItem.comment?.kind &&
                        oldItem.comment?.data?.id == newItem.comment?.data?.id

        override fun areContentsTheSame(oldItem: CommentOrSelfText, newItem: CommentOrSelfText): Boolean =
                oldItem.selfText == newItem.selfText &&
                        oldItem.comment?.kind == newItem.comment?.kind &&
                        oldItem.comment?.data?.id == newItem.comment?.data?.id &&
                        oldItem.comment?.data?.editedTime == newItem.comment?.data?.editedTime &&
                        oldItem.comment?.data?.score_hidden == newItem.comment?.data?.score_hidden &&
                        oldItem.comment?.data?.score == newItem.comment?.data?.score
    }

    data class CommentOrSelfText(
            val selfText: String? = null,
            val comment: Thing? = null
    )

}