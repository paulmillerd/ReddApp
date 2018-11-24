package com.paulmillerd.redditapp.ui.subreddit

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.getAgeString
import com.paulmillerd.redditapp.toMagnitudeString
import com.paulmillerd.redditapp.ui.VoteCallback
import kotlinx.android.synthetic.main.subreddit_item.view.*
import java.text.NumberFormat

class SubredditViewHolder(
        itemView: View,
        private val fragmentCallback: SubredditFragment.SubredditFragmentCallback?,
        private val voteCallback: VoteCallback?
): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup,
                   fragmentCallback: SubredditFragment.SubredditFragmentCallback?,
                   voteCallback: VoteCallback?) =
                SubredditViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.subreddit_item, parent, false),
                        fragmentCallback,
                        voteCallback)
    }

    fun bindChild(childrenItem: Thing?) {
        with (itemView) {
            childrenItem?.data?.let { data ->
                if (data.isSelf == true) {
                    domain.visibility = GONE
                    link_icon.visibility = GONE
                } else {
                    link_icon.visibility = VISIBLE
                    domain.visibility = VISIBLE
                    domain.text = data.domain
                }
                title.text = data.title ?: ""
                subreddit.text = data.subredditNamePrefixed ?: ""
                username.text = data.author ?: ""
                age.text = data.createdUtc?.let { getAgeString(it, context) } ?: ""
                val thumbnailUrl = data.thumbnail
                if (URLUtil.isValidUrl(thumbnailUrl)) {
                    thumbnail.visibility = VISIBLE
                    Glide.with(context)
                            .load(thumbnailUrl)
                            .into(thumbnail)
                } else {
                    thumbnail.visibility = GONE
                }
                comments.text = NumberFormat.getNumberInstance().format(data.numComments)
                if (!TextUtils.isEmpty(data.linkFlairText)) {
                    flair_badge.visibility = VISIBLE
                    flair_badge.text = data.linkFlairText
                } else {
                    flair_badge.visibility = GONE
                }
                if (data.over18 == true) {
                    nsfw_badge.visibility = VISIBLE
                } else {
                    nsfw_badge.visibility = GONE
                }

                setOnClickListener {
                    fragmentCallback?.onPostTapped(childrenItem)
                }
            }

            score.text = childrenItem?.tempScore?.toMagnitudeString(context)

            score.setTextColor(ContextCompat.getColor(context,
                    when (childrenItem?.tempLikes) {
                        null -> R.color.textColorDefault
                        true -> R.color.colorAccent
                        false -> R.color.colorPrimary
                    }))

            upvote.setColorFilter(ContextCompat.getColor(context,
                    when (childrenItem?.tempLikes) {
                        null, false -> R.color.textColorDefault
                        true -> R.color.colorAccent
                    }))

            downvote.setColorFilter(ContextCompat.getColor(context,
                    when (childrenItem?.tempLikes) {
                        null, true -> R.color.textColorDefault
                        false -> R.color.colorPrimary
                    }))

            childrenItem?.let { item ->
                upvote.setOnClickListener { voteCallback?.upvoteTapped(item) }
                downvote.setOnClickListener { voteCallback?.downvoteTapped(item) }
            }
        }
    }

}