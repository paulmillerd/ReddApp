package com.paulmillerd.redditapp.ui.comments

import android.view.View

abstract class CommentListViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    abstract fun bindView(item: CommentsAdapter.CommentOrSelfText)

}