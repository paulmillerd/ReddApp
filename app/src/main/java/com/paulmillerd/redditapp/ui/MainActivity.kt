package com.paulmillerd.redditapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.ui.account.AccountFragment
import com.paulmillerd.redditapp.ui.browse.BrowseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceMainFragment(BrowseFragment::class.java)
        bottom_nav_bar.setOnNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment?.isVisible == true &&
                    fragment.childFragmentManager.backStackEntryCount > 0) {
                fragment.childFragmentManager.popBackStack()
                return
            }
        }
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean =
            showSelectedFragment(item.itemId)

    override fun onNavigationItemReselected(item: MenuItem) {
        showSelectedFragment(item.itemId)
    }

    private fun showSelectedFragment(menuItemId: Int): Boolean {
        return when (menuItemId) {
            R.id.action_browse -> {
                replaceMainFragment(BrowseFragment::class.java)
                true
            }
            R.id.action_account -> {
                replaceMainFragment(AccountFragment::class.java)
                true
            }
            else -> false
        }
    }

    private fun <T: Fragment> replaceMainFragment(fragmentType: Class<T>) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragmentType.newInstance())
                .commit()
    }

}
