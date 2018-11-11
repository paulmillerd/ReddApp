package com.paulmillerd.redditapp.ui.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.text.TextUtils
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.listing.ChildrenItem
import com.paulmillerd.redditapp.api.responseModels.subredditAbout.AboutResponse
import com.paulmillerd.redditapp.repository.ListingRepository
import com.paulmillerd.redditapp.repository.SubredditAboutRepository

class ListingViewModel: ViewModel() {

    private lateinit var listingRepository: ListingRepository
    private lateinit var mAboutRepository: SubredditAboutRepository
    private val subreddit = MutableLiveData<String>()
    val listing: LiveData<PagedList<ChildrenItem>> = Transformations.switchMap(subreddit) {
        listingRepository.getListing(it, SortOrder.BEST)
    }
    val subredditAbout: LiveData<AboutResponse?> = Transformations.switchMap(subreddit)  {
        if (!TextUtils.isEmpty(it))
            mAboutRepository.getSubredditAbout(it)
        else
            MutableLiveData<AboutResponse>().also { liveData ->
                liveData.postValue(null)
            }
    }

    fun init(listingRepository: ListingRepository, aboutRepository: SubredditAboutRepository) {
        this.listingRepository = listingRepository
        this.mAboutRepository = aboutRepository
    }

    fun setSubreddit(newSubreddit: String) {
        subreddit.postValue(newSubreddit)
    }

}