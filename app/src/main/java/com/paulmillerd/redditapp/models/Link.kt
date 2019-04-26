package com.paulmillerd.redditapp.models

data class Link(
        val id: String,
        val author: String,
        val title: String,
        val subreddit: String,
        val score: Int,
        val url: String?,
        val thumbnail: String?,
        val selfText: String?
) : Thing()