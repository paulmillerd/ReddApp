package com.paulmillerd.redditapp

import android.app.Application
import android.content.Context
import com.paulmillerd.redditapp.di.AppComponent
import com.paulmillerd.redditapp.di.DaggerAppComponent
import com.paulmillerd.redditapp.di.module.RedditApiModule

class RedditApp: Application() {

    companion object {
        fun getAppComponent(context: Context): AppComponent {
            return (context.applicationContext as RedditApp).component
        }
    }

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .redditApiModule(RedditApiModule(this))
                .build()
    }

}