package com.paulmillerd.redditapp.models

data class Comment(
        val id: String,
        val author: String,
        val score: Int,
        val body: String,
        val replies: Listing
) : Thing()