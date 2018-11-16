package com.paulmillerd.redditapp.ui.subredditPicker

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.URLUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.subredditAutocomplete.SubredditsItem
import com.paulmillerd.redditapp.toMagnitudeString
import kotlinx.android.synthetic.main.autocomplete_item.view.*

class AutocompleteViewHolder(itemView: View, val callback: AutocompleteVhCallback) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

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
                    Glide.with(context)
                            .load(item.icon)
                            .apply(RequestOptions.circleCropTransform())
                            .into(subreddit_icon)
                } else {
                    subreddit_icon.visibility = INVISIBLE
                }
                subscribers.text = String.format(context.getString(R.string.subscribers),
                        item.numSubscribers?.toMagnitudeString(context) ?: "")
                setOnClickListener { callback.onItemTapped(item.name) }
            }
        }
    }

    interface AutocompleteVhCallback {
        fun onItemTapped(subredditName: String?)
    }

}