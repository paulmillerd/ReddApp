package com.paulmillerd.redditapp.ui.subredditPicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
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