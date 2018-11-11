package com.paulmillerd.redditapp.ui.listingPicker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.URLUtil
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.subredditAutocomplete.SubredditsItem
import com.paulmillerd.redditapp.toThousandsString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.autocomplete_item.view.*

class AutocompleteViewHolder(itemView: View, val callback: AutocompleteVhCallback) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup, callback: AutocompleteVhCallback) =
                AutocompleteViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.autocomplete_item, parent, false),
                        callback
                )
    }

    fun bindView(subreddit: SubredditsItem?) {
        with (itemView) {
            subreddit?.let { item ->
                subreddit_name.text = item.name
                if (URLUtil.isValidUrl(item.icon)) {
                    subreddit_icon.visibility = VISIBLE
                    Picasso.get().load(item.icon).into(subreddit_icon)
                } else {
                    subreddit_icon.visibility = INVISIBLE
                }
                subscribers.text = String.format(context.getString(R.string.subscribers),
                        item.numSubscribers?.toThousandsString(context) ?: "")
                setOnClickListener { callback.onItemTapped(item.name) }
            }
        }
    }

    interface AutocompleteVhCallback {
        fun onItemTapped(subredditName: String?)
    }

}