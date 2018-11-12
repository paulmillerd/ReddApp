package com.paulmillerd.redditapp.ui.subredditPicker

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.paulmillerd.redditapp.repository.AutocompleteRepository

class SubredditPickerViewModel: ViewModel() {

    private lateinit var autocompleteRepository: AutocompleteRepository
    private val query = MutableLiveData<String>()
    val autocompleteResponse = Transformations.switchMap(query) {
        autocompleteRepository.getSubredditAutocomplete(it)
    }

    fun init(autocompleteRepository: AutocompleteRepository) {
        this.autocompleteRepository = autocompleteRepository
    }

    fun setQuery(query: String) {
        this.query.postValue(query)
    }

}