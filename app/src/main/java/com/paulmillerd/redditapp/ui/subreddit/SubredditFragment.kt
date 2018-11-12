package com.paulmillerd.redditapp.ui.subreddit

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
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.repository.SubredditAboutRepository
import com.paulmillerd.redditapp.repository.SubredditRepository
import com.paulmillerd.redditapp.ui.SubredditInterface
import kotlinx.android.synthetic.main.fragment_subreddit.*
import javax.inject.Inject

class SubredditFragment: Fragment(), SubredditInterface {

    companion object {
        const val SUBREDDIT = "SUBREDDIT"
        const val SORT_ORDER = "SORT_ORDER"
    }

    @Inject
    lateinit var mSubredditRepository: SubredditRepository
    @Inject
    lateinit var mAboutRepository: SubredditAboutRepository

    private lateinit var viewModel: SubredditViewModel
    private val listingAdapter = SubredditAdapter()
    var callback: SubredditFragmentCallback? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subreddit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_recycler_view.apply {
            listingAdapter.callback = callback
            adapter = listingAdapter
            layoutManager = LinearLayoutManager(context)
        }
        toolbar.setOnClickListener {
            callback?.onToolbarTapped()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            RedditApp.getAppComponent(it).inject(this)

            viewModel = ViewModelProviders.of(this).get(SubredditViewModel::class.java)
            viewModel.init(mSubredditRepository, mAboutRepository)
            viewModel.listing.observe(this, Observer { children ->
                populateRecyclerView(children)
            })
            viewModel.subredditAbout.observe(this, Observer { aboutResponse ->
                if (aboutResponse == null) toolbar.title = getString(R.string.front_page)
                else toolbar.title = aboutResponse.data?.displayNamePrefixed
            })
            viewModel.setSubreddit(arguments?.getString(SUBREDDIT) ?: "")
        }
    }

    override fun setSubreddit(newSubreddit: String) {
        viewModel.setSubreddit(newSubreddit)
    }

    private fun populateRecyclerView(children: PagedList<Thing>?) {
        listingAdapter.submitList(children)
        listingAdapter.notifyDataSetChanged()
        list_recycler_view.scheduleLayoutAnimation()
    }

    interface SubredditFragmentCallback {
        fun onToolbarTapped()
        fun onPostTapped(post: Thing)
    }

}