package com.paulmillerd.redditapp.ui.subreddit

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.URLUtil
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.getAgeString
import com.paulmillerd.redditapp.toMagnitudeString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.subreddit_item.view.*
import java.text.NumberFormat

class SubredditViewHolder(itemView: View, val callback: SubredditFragment.SubredditFragmentCallback?):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup, callback: SubredditFragment.SubredditFragmentCallback?) =
                SubredditViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.subreddit_item, parent, false),
                        callback)
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
                    Picasso.get().load(thumbnailUrl).into(thumbnail)
                } else {
                    thumbnail.visibility = GONE
                }
                comments.text = NumberFormat.getNumberInstance().format(data.numComments)
                score.text = data.score?.toMagnitudeString(context)
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
                    callback?.onPostTapped(childrenItem)
                }
            }
        }
    }

}