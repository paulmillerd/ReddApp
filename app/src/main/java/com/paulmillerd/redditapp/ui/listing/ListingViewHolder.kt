package com.paulmillerd.redditapp.ui.listing

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.URLUtil
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.ChildrenItem
import com.paulmillerd.redditapp.toThousandsString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listing_item_layout.view.*

class ListingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindChild(childrenItem: ChildrenItem?) {
        with (itemView) {
            childrenItem?.data?.let { data ->
                if (data.isSelf == true) {
                    domain.visibility = GONE
                } else {
                    domain.visibility = VISIBLE
                    domain.text = data.domain
                }
                title.text = data.title ?: ""
                info.text = String.format(
                        "%s • %s",
                        data.subredditNamePrefixed,
                        data.author)
                val thumbnailUrl = data.thumbnail
                if (URLUtil.isValidUrl(thumbnailUrl)) {
                    thumbnail.visibility = VISIBLE
                    Picasso.get().load(thumbnailUrl).into(thumbnail)
                } else {
                    thumbnail.visibility = GONE
                }
                comments.text = String.format(
                        "%s %s",
                        data.numComments?.toThousandsString(),
                        context.getString(R.string.comments)
                )
                score.text = data.score?.toThousandsString()
            }
        }
    }

}