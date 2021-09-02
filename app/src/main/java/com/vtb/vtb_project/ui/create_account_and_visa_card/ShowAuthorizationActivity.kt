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
    private var showAuthorization: ActivityShowAuthorizationBinding? = null
    private lateinit var showTabViewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        showAuthorization = ActivityShowAuthorizationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(showAuthorization?.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }


        val tabName: Array<String> =
            resources.getStringArray(R.array.tab_view_pager_array)   //get virtual cars tab names


        showAuthorization?.pager?.let {
            showTabViewPager = it
        }
        val adapterViewPager = AuthorizationViewPagerAdapter(
            ShowAuthorizationNoCardFragment(),
            supportFragmentManager, lifecycle
        )
        showTabViewPager.adapter = adapterViewPager

        showAuthorization?.tabLayout?.let {
            TabLayoutMediator(it, showTabViewPager) { tab, position ->
                tab.text = tabName[position]

            }.attach()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        showAuthorization = null
    }
}