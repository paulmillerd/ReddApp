package com.paulmillerd.redditapp.ui.floatingNavBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paulmillerd.redditapp.R

class FloatingNavBarFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.floating_nav_bar_layout, container, false)
    }

}