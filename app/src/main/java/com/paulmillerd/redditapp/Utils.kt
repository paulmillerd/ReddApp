package com.paulmillerd.redditapp

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


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

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

enum class SortOrder(val pathParam: String) {
    BEST("best"),
    HOT("hot"),
    NEW("new"),
    TOP("top"),
    CONTROVERSIAL("controversial"),
    RISING("rising")
}