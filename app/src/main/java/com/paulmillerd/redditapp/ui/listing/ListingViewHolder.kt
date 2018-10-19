package com.paulmillerd.redditapp.ui.listing

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.URLUtil
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.ChildrenItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listing_item_layout.view.*

class ListingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindChild(childrenItem: ChildrenItem?) {
        with (itemView) {
            if (childrenItem?.data?.isSelf == true) {
                domain.visibility = GONE
            } else {
                domain.visibility = VISIBLE
                domain.text = childrenItem?.data?.domain
            }
            title.text = childrenItem?.data?.title ?: ""
            info.text = String.format(
                    "%s • %s",
                    childrenItem?.data?.subredditNamePrefixed,
                    childrenItem?.data?.author
            )
            val thumbnailUrl = childrenItem?.data?.thumbnail
            if (URLUtil.isValidUrl(thumbnailUrl)) {
                thumbnail.visibility = VISIBLE
                Picasso.get().load(thumbnailUrl).into(thumbnail)
            } else {
                thumbnail.visibility = GONE
            }
            comments.text = String.format(
                    "%s %s",
                    childrenItem?.data?.numComments,
                    context.getString(R.string.comments)
            )
            score.text = childrenItem?.data?.score.toString()
        }

    }

}