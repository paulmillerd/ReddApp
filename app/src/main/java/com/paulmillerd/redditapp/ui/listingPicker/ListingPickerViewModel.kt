package com.paulmillerd.redditapp.ui.listingPicker

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.paulmillerd.redditapp.repository.SubredditAboutRepositiory

class ListingPickerViewModel: ViewModel() {

    private lateinit var subredditAboutRepository: SubredditAboutRepositiory
    private val subreddit = MutableLiveData<String>()
    val subredditAbout = Transformations.switchMap(subreddit) {
        subredditAboutRepository.getSubredditAbout(it)
    }

    fun init(subredditAboutRepository: SubredditAboutRepositiory) {
        this.subredditAboutRepository = subredditAboutRepository
    }

    fun setSubreddit(newSubreddit: String) {
        subreddit.postValue(newSubreddit)
    }

}