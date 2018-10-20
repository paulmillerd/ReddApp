package com.paulmillerd.redditapp

fun Int.toThousandsString(): String =
    if (this >= 1000) {
        val thousands = this.toFloat() / 1000f
        String.format("%.1fk", thousands)
    } else {
        this.toString()
    }
