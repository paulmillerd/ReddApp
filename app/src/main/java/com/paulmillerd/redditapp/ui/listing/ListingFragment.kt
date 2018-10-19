package com.paulmillerd.redditapp.ui.listing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.api.RedditService
import kotlinx.android.synthetic.main.list_fragment_layout.*
import javax.inject.Inject

class ListingFragment: Fragment() {

    @Inject
    lateinit var redditService: RedditService

    lateinit var viewModel: ListingViewModel
    private val listingAdapter = ListingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_recycler_view.adapter = listingAdapter
        list_recycler_view.layoutManager = LinearLayoutManager(context)
        context?.let {
            RedditApp.getAppComponent(it).inject(this)
            viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
            viewModel.init(redditService)
            viewModel.getBestListing().observe(this, Observer { response ->
                response?.data?.children?.let { children ->
                    listingAdapter.children.addAll(children)
                    listingAdapter.notifyDataSetChanged()
                }
            })
        }

    }

}