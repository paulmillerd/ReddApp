package com.paulmillerd.redditapp.ui.post

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.ui.LinkFragment
import com.paulmillerd.redditapp.ui.comments.CommentsFragment

class PostViewPagerAdapter(fragmentManager: FragmentManager, postData: Thing, context: Context):
        FragmentPagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<Fragment>()
    private val pageTitles = mutableListOf<String>()

    init {
        if (postData.data?.isSelf == false)  {
            fragments.add(LinkFragment().also { fragment ->
                fragment.arguments = Bundle().also { bundle ->
                    bundle.putString(LinkFragment.URL, postData.data.url)
                }
            })
            pageTitles.add(context.getString(R.string.link))
        }

        fragments.add(CommentsFragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putSerializable(CommentsFragment.POST_DATA, postData)
            }
            pageTitles.add(context.getString(R.string.comments_title_case))
        })
    }

    override fun getPageTitle(position: Int): CharSequence? = pageTitles[position]

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}