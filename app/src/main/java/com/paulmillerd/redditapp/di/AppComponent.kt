package com.paulmillerd.redditapp.di

import com.paulmillerd.redditapp.di.module.RedditApiModule
import com.paulmillerd.redditapp.ui.listing.ListingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ RedditApiModule::class ])
interface AppComponent {
    fun inject(listingFragment: ListingFragment)
}