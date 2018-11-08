package com.paulmillerd.redditapp.ui.listing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.api.responseModels.Listing.ChildrenItem
import com.paulmillerd.redditapp.repository.ListingRepository
import kotlinx.android.synthetic.main.fragment_listing.*
import javax.inject.Inject

class ListingFragment: Fragment(), ListingInterface {

    companion object {
        const val SUBREDDIT = "SUBREDDIT"
        const val SORT_ORDER = "SORT_ORDER"
    }

    @Inject
    lateinit var listingRepository: ListingRepository

    private lateinit var viewModel: ListingViewModel
    private val listingAdapter = ListingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_recycler_view.apply {
            adapter = listingAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            RedditApp.getAppComponent(it).inject(this)

            viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
            viewModel.init(listingRepository)
            viewModel.listing.observe(this, Observer { children ->
                populateRecyclerView(children)
            })
        }
    }

    override fun setSubreddit(newSubreddit: String) {
        viewModel.setSubreddit(newSubreddit)
    }

    private fun populateRecyclerView(children: PagedList<ChildrenItem>?) {
        listingAdapter.submitList(children)
        listingAdapter.notifyDataSetChanged()
        list_recycler_view.scheduleLayoutAnimation()
    }

}