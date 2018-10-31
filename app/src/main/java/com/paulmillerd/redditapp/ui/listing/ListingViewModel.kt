package com.paulmillerd.redditapp.ui.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.Listing.ListingResponse
import com.paulmillerd.redditapp.repository.ListingRepository

class ListingViewModel: ViewModel() {

    fun getListing(listingRepository: ListingRepository,
                   subreddit: String?,
                   sortOrder: SortOrder?): LiveData<ListingResponse> =
            listingRepository.getListing(subreddit, sortOrder ?: SortOrder.BEST)

}