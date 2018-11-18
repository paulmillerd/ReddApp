package com.paulmillerd.redditapp.ui.subreddit

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.api.responseModels.subredditAbout.AboutResponse
import com.paulmillerd.redditapp.repository.SubredditAboutRepository
import com.paulmillerd.redditapp.repository.SubredditRepository

class SubredditViewModel: ViewModel() {

    private lateinit var mSubredditRepository: SubredditRepository
    private lateinit var mAboutRepository: SubredditAboutRepository
    private val subreddit = MutableLiveData<String>()
    val listing: LiveData<PagedList<Thing>> = Transformations.switchMap(subreddit) {
        mSubredditRepository.getListing(it, SortOrder.BEST)
    }
    val subredditAbout: LiveData<AboutResponse?> = Transformations.switchMap(subreddit)  {
        if (!TextUtils.isEmpty(it))
            mAboutRepository.getSubredditAbout(it)
        else
            MutableLiveData<AboutResponse>().also { liveData ->
                liveData.postValue(null)
            }
    }

    fun init(subredditRepository: SubredditRepository, aboutRepository: SubredditAboutRepository) {
        this.mSubredditRepository = subredditRepository
        this.mAboutRepository = aboutRepository
    }

    fun setSubreddit(newSubreddit: String) {
        subreddit.postValue(newSubreddit)
    }

    fun isSubredditSet(): Boolean = subreddit.value != null

}