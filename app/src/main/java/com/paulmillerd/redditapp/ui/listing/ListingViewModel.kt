package com.paulmillerd.redditapp.ui.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.Listing.ChildrenItem
import com.paulmillerd.redditapp.repository.ListingRepository

class ListingViewModel: ViewModel() {

    private lateinit var listingRepository: ListingRepository
    private val subreddit = MutableLiveData<String>()
    val listing: LiveData<PagedList<ChildrenItem>> = Transformations.switchMap(subreddit) {
        listingRepository.getListing(it, SortOrder.BEST)
    }

    fun init(listingRepository: ListingRepository) {
        this.listingRepository = listingRepository
        subreddit.postValue("")
    }

    fun setSubreddit(newSubreddit: String) {
        subreddit.postValue(newSubreddit)
    }

}