package com.paulmillerd.redditapp.ui.subredditPicker

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.hideKeyboard
import com.paulmillerd.redditapp.repository.AutocompleteRepository
import com.paulmillerd.redditapp.showKeyboard
import kotlinx.android.synthetic.main.fragment_subreddit_picker.*
import javax.inject.Inject

class SubredditPickerFragment: Fragment(), AutocompleteViewHolder.AutocompleteVhCallback {

    @Inject
    lateinit var autocompleteRepository: AutocompleteRepository

    var callback: SubredditPickerCallback? = null
    private lateinit var viewModel: SubredditPickerViewModel
    private val autocompleteAdapter = AutocompleteAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subreddit_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autocomplete_list.layoutManager = LinearLayoutManager(context)
        autocomplete_list.adapter = autocompleteAdapter

        back_arrow.setOnClickListener {
            activity?.let { activity ->
                hideKeyboard(activity)
            }
            activity?.onBackPressed()
        }

        subreddit_edit_text.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                context?.let { showKeyboard(it) }
        }
        subreddit_edit_text.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setQuery(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            RedditApp.getAppComponent(it).inject(this)
            viewModel = ViewModelProviders.of(this).get(SubredditPickerViewModel::class.java)
            viewModel.init(autocompleteRepository)
            viewModel.autocompleteResponse.observe(this, Observer { response ->
                response?.subreddits?.let {
                    autocompleteAdapter.items = it
                    autocompleteAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onItemTapped(subredditName: String?) {
        subredditName?.let {
            callback?.onSubredditEntered(it)
        }
    }

    fun requestFocus() {
        subreddit_edit_text.requestFocus()
    }

    interface SubredditPickerCallback {
        fun onSubredditEntered(subreddit: String)
    }

}