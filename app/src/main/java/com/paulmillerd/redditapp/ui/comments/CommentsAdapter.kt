package com.paulmillerd.redditapp.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.paulmillerd.redditapp.api.responseModels.listing.Thing

class CommentsAdapter: RecyclerView.Adapter<CommentViewHolder>() {

    var commentList = listOf<Thing?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
            CommentViewHolder.create(parent)

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindView(commentList[position])
    }

}