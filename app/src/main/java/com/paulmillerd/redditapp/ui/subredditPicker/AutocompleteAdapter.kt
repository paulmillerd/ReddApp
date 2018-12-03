package com.paulmillerd.redditapp.ui.subredditPicker

import android.view.ViewGroup
import com.paulmillerd.redditapp.redditApi.responseModels.subredditAutocomplete.SubredditsItem

class AutocompleteAdapter(val callback: AutocompleteViewHolder.AutocompleteVhCallback):
        androidx.recyclerview.widget.RecyclerView.Adapter<AutocompleteViewHolder>() {

    var items = listOf<SubredditsItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutocompleteViewHolder =
            AutocompleteViewHolder.create(parent, callback)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: AutocompleteViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }

}