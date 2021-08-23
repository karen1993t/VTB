package com.vtb.vtb_project.ui.create_account_and_visa_card


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.AuthorizationViewPagerAdapter
import com.vtb.vtb_project.databinding.ActivityShowAuthorizationBinding


class ShowAuthorizationActivity : AppCompatActivity() {
    private lateinit var bindingShow: ActivityShowAuthorizationBinding
    private lateinit var showTabViewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        bindingShow = ActivityShowAuthorizationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingShow.root)

        val tabName: Array<String> = arrayOf(
            "Me", "Booking", "Favorites"
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        showTabViewPager = bindingShow.pager
        val adapterViewPager = AuthorizationViewPagerAdapter(
            ShowAuthorizationNoCardFragment(),
            supportFragmentManager, lifecycle
        )
        showTabViewPager.adapter = adapterViewPager

        val tableLayout = bindingShow.tabLayout
        TabLayoutMediator(tableLayout, showTabViewPager) { tab, position ->
            tab.text = tabName[position]

        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //  return super.onOptionsItemSelected(item)
    // }
}