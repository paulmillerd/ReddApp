package com.paulmillerd.redditapp.ui.subreddit

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing
import com.paulmillerd.redditapp.redditApi.responseModels.subredditAbout.AboutResponse
import com.paulmillerd.redditapp.repository.SubredditAboutRepository
import com.paulmillerd.redditapp.repository.SubredditRepository
import com.paulmillerd.redditapp.serviceManager.VotingManager
import com.paulmillerd.redditapp.ui.VoteCallback

class SubredditViewModel: ViewModel(), VoteCallback {

    private lateinit var mSubredditRepository: SubredditRepository
    private lateinit var mAboutRepository: SubredditAboutRepository
    private lateinit var mVotingManager: VotingManager
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
    private val _dataUpdates = MutableLiveData<Boolean>()
    val dataUpdates: LiveData<Boolean> get() = _dataUpdates

    fun init(subredditRepository: SubredditRepository,
             aboutRepository: SubredditAboutRepository,
             votingManager: VotingManager) {
        this.mSubredditRepository = subredditRepository
        this.mAboutRepository = aboutRepository
        this.mVotingManager = votingManager
    }

    fun setSubreddit(newSubreddit: String) {
        subreddit.postValue(newSubreddit)
    }

    fun isSubredditSet(): Boolean = subreddit.value != null

    override fun upvoteTapped(thing: Thing) {
        when (thing.tempLikes) {
            false, null -> {
                thing.data?.name?.let {
                    mVotingManager.upvote(it)
                }
                thing.tempLikes = true
                if (thing.tempScore != null) thing.tempScore = thing.tempScore!! + 1
            }
            true -> {
                thing.data?.name?.let { mVotingManager.resetVote(it) }
                thing.tempLikes = null
                if (thing.tempScore != null) thing.tempScore = thing.tempScore!! - 1
            }
        }
        _dataUpdates.postValue(true)
    }

    override fun downvoteTapped(thing: Thing) {
        when (thing.tempLikes) {
            true, null -> {
                thing.data?.name?.let { mVotingManager.downvote(it) }
                thing.tempLikes = false
                if (thing.tempScore != null) thing.tempScore = thing.tempScore!! - 1
            }
            false -> {
                thing.data?.name?.let { mVotingManager.resetVote(it) }
                thing.tempLikes = null
                if (thing.tempScore != null) thing.tempScore = thing.tempScore!! + 1
            }
        }
        _dataUpdates.postValue(true)
    }

}