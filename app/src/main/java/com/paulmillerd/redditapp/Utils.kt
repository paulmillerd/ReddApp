package com.paulmillerd.redditapp

import android.content.Context

fun Int.toThousandsString(context: Context): String =
    if (this >= 1000) {
        val thousands = this.toFloat() / 1000f
        String.format(context.getString(R.string.thousands_formatter), thousands)
    } else {
        this.toString()
    }

fun getAgeString(createdUtc: Int, context: Context): String {
    val age = (System.currentTimeMillis() / 1000) - createdUtc
    return if (age < 3600) {
        val minutes = age / 60
        if (minutes == 1L) {
            context.getString(R.string.one_minute)
        } else {
            String.format(context.getString(R.string.minute_formatter), minutes)
        }
    } else {
        val hours = age / 3600
        if (hours == 1L) {
            context.getString(R.string.one_hour)
        } else {
            String.format(context.getString(R.string.hours_formatter), hours)
        }
    }
}

enum class SortOrder(val pathParam: String) {
    BEST("best"),
    HOT("hot"),
    NEW("new"),
    TOP("top"),
    CONTROVERSIAL("controversial"),
    RISING("rising")
}