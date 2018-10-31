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
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.Listing.ChildrenItem
import com.paulmillerd.redditapp.repository.ListingRepository
import kotlinx.android.synthetic.main.list_fragment_layout.*
import javax.inject.Inject

class ListingFragment: Fragment() {

    companion object {
        const val SUBREDDIT = "SUBREDDIT"
        const val SORT_ORDER = "SORT_ORDER"
    }

    @Inject
    lateinit var listingRepository: ListingRepository

    private lateinit var viewModel: ListingViewModel
    private val listingAdapter = ListingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_recycler_view.apply {
            adapter = listingAdapter
            layoutManager = LinearLayoutManager(context)
        }

        context?.let {
            RedditApp.getAppComponent(it).inject(this)

            viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
            viewModel.getListing(
                    listingRepository,
                    arguments?.getString(SUBREDDIT),
                    arguments?.getSerializable(SORT_ORDER) as SortOrder?
            ).observe(this, Observer { response ->
                response?.data?.children?.let { children ->
                    populateRecyclerView(children)
                }
            })
        }
    }

    private fun populateRecyclerView(children: List<ChildrenItem?>) {
        listingAdapter.children.addAll(children)
        listingAdapter.notifyDataSetChanged()
        list_recycler_view.scheduleLayoutAnimation()
    }

}