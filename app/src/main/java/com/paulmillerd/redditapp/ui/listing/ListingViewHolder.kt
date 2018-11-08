package com.paulmillerd.redditapp.ui.listing

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.URLUtil
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.Listing.ChildrenItem
import com.paulmillerd.redditapp.getAgeString
import com.paulmillerd.redditapp.toThousandsString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listing_item.view.*
import java.text.NumberFormat

class ListingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindChild(childrenItem: ChildrenItem?) {
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
                subreddit.text = data.subredditNamePrefixed
                info.text = String.format(
                        "%s • %s",
                        data.author,
                        data.createdUtc?.let { getAgeString(it, context) } ?: "")
                val thumbnailUrl = data.thumbnail
                if (URLUtil.isValidUrl(thumbnailUrl)) {
                    thumbnail.visibility = VISIBLE
                    Picasso.get().load(thumbnailUrl).into(thumbnail)
                } else {
                    thumbnail.visibility = GONE
                }
                comments.text = String.format(
                        "%s %s",
                        NumberFormat.getNumberInstance().format(data.numComments),
                        context.getString(R.string.comments)
                )
                score.text = data.score?.toThousandsString(context)
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
            }
        }
    }

}