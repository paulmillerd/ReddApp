package com.paulmillerd.redditapp

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Int.toMagnitudeString(context: Context): String =
        when {
            this >= 1_000_000_000 -> {
                val billions = toFloat() / 1_000_000_000f
                String.format(context.getString(R.string.billions_formatter), billions)
            }
            this >= 1_000_000 -> {
                val millions = toFloat() / 1_000_000f
                String.format(context.getString(R.string.millions_formatter), millions)
            }
            this >= 1000 -> {
                val thousands = toFloat() / 1000f
                String.format(context.getString(R.string.thousands_formatter), thousands)
            }
            else -> {
                toString()
            }
        }

const val MINUTE = 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24
const val WEEK = DAY * 7
const val YEAR = WEEK * 52

fun getAgeString(createdUtc: Int, context: Context): String {
    val age = (System.currentTimeMillis() / 1000) - createdUtc
    return when {
        age < MINUTE -> {
            if (age <= 1) {
                context.getString(R.string.one_second)
            } else {
                String.format(context.getString(R.string.second_formatter), age)
            }
        }
        age < HOUR -> {
            val minutes = age / MINUTE
            if (minutes == 1L) {
                context.getString(R.string.one_minute)
            } else {
                String.format(context.getString(R.string.minute_formatter), minutes)
            }
        }
        age < DAY -> {
            val hours = age / HOUR
            if (hours == 1L) {
                context.getString(R.string.one_hour)
            } else {
                String.format(context.getString(R.string.hours_formatter), hours)
            }
        }
        age < WEEK -> {
            val days = age / DAY
            if (days == 1L) {
                context.getString(R.string.one_day)
            } else {
                String.format(context.getString(R.string.days_formatter), days)
            }
        }
        age < YEAR -> {
            val weeks = age / WEEK
            if (weeks == 1L) {
                context.getString(R.string.one_week)
            } else {
                String.format(context.getString(R.string.week_formatter), weeks)
            }
        }
        else -> {
            val years = age / YEAR
            if (years == 1L) {
                context.getString(R.string.one_year)
            } else {
                String.format(context.getString(R.string.year_formatter), years)
            }
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

fun showKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

enum class SortOrder(val pathParam: String) {
    BEST("best"),
    HOT("hot"),
    NEW("new"),
    TOP("top"),
    CONTROVERSIAL("controversial"),
    RISING("rising")
}