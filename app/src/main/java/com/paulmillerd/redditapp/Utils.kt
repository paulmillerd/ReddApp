package com.paulmillerd.redditapp

import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import android.view.inputmethod.InputMethodManager

enum class ThingType(val prefix: String) {
    COMMENT("t1"),
    ACCOUNT("t2"),
    LINK("t3"),
    MESSAGE("t4"),
    SUBREDDIT("t5"),
    AWARD("t6")
}

const val BILLION = 1_000_000_000
const val MILLION = 1_000_000
const val THOUSAND = 1_000

fun Int.toMagnitudeString(context: Context): String =
        when {
            this < THOUSAND -> toString()
            this < MILLION -> {
                val thousands = toFloat() / THOUSAND.toFloat()
                String.format(context.getString(R.string.thousands_formatter), thousands)
            }
            this < BILLION -> {
                val millions = toFloat() / MILLION.toFloat()
                String.format(context.getString(R.string.millions_formatter), millions)
            }
            else -> {
                val billions = toFloat() / BILLION.toFloat()
                String.format(context.getString(R.string.billions_formatter), billions)
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
        age < MINUTE -> getQuantityString(age, 1, R.string.one_second, R.string.second_formatter, context)
        age < HOUR -> getQuantityString(age, MINUTE, R.string.one_minute, R.string.minute_formatter, context)
        age < DAY -> getQuantityString(age, HOUR, R.string.one_hour, R.string.hours_formatter, context)
        age < WEEK -> getQuantityString(age, DAY, R.string.one_day, R.string.days_formatter, context)
        age < YEAR -> getQuantityString(age, WEEK, R.string.one_week, R.string.week_formatter, context)
        else -> getQuantityString(age, YEAR, R.string.one_year, R.string.year_formatter, context)
    }
}

fun getQuantityString(age: Long, divisor: Int, @StringRes singularStringRes: Int,
                @StringRes pluralStringRes: Int, context: Context): String {
    val quantity = age / divisor
    return if (quantity <= 1) context.getString(singularStringRes)
    else String.format(context.getString(pluralStringRes), quantity)
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