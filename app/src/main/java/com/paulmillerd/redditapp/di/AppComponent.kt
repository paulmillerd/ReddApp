package com.paulmillerd.redditapp.di

import com.paulmillerd.redditapp.di.module.RedditApiModule
import com.paulmillerd.redditapp.ui.listing.ListingFragment
import com.paulmillerd.redditapp.ui.listingPicker.ListingPickerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ RedditApiModule::class ])
interface AppComponent {
    fun inject(listingFragment: ListingFragment)
    fun inject(listingPickerFragment: ListingPickerFragment)
}