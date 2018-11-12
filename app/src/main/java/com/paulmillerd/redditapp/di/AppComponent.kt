package com.paulmillerd.redditapp.di

import com.paulmillerd.redditapp.di.module.RedditApiModule
import com.paulmillerd.redditapp.ui.comments.CommentsFragment
import com.paulmillerd.redditapp.ui.subreddit.SubredditFragment
import com.paulmillerd.redditapp.ui.subredditPicker.SubredditPickerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ RedditApiModule::class ])
interface AppComponent {
    fun inject(subredditFragment: SubredditFragment)
    fun inject(subredditPickerFragment: SubredditPickerFragment)
    fun inject(commentsFragment: CommentsFragment)
}