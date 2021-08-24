package com.vtb.vtb_project.ui.on_boarding_about_fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.ViewPagerAdapter
import com.vtb.vtb_project.ui.authorization.Authorization
import com.vtb.vtb_project.databinding.ActivityOnboardingAboutBinding
import com.vtb.vtb_project.view_model.ViewModelOnBoarding

class OnBoardingAbout : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingAbout = ActivityOnboardingAboutBinding.inflate(layoutInflater)
        setContentView(bindingAbout.root)

        viewPager2 = bindingAbout.viewPager2
        tabLayout = bindingAbout.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        bindingAbout.viewPager2.adapter = adapter

        TabLayoutMediator(bindingAbout.tabLayout, bindingAbout.viewPager2) { tab, position ->
        }.attach()

        viewPager2.setPageTransformer(MarginPageTransformer(resources.getDimensionPixelSize(R.dimen.start_margin_fragments)))

        bindingAbout.bgButtonNext.setOnClickListener {
            viewPager2.currentItem += 1
        }

        val liveDataGetStarted = ViewModelProvider(this).get(ViewModelOnBoarding::class.java)

        liveDataGetStarted.closeButtonLiveData.observe(this, {
            if (it) {
                bindingAbout.ellipse.setOnClickListener {
                    startActivity(Intent(this, Authorization::class.java))
                    finish()
                }
            }
        })

        liveDataGetStarted.getStartedLiveData.observe(this, {
            if (it) {
                bindingAbout.headline.text = resources.getString(R.string.get_started)
                bindingAbout.bgButtonNext.setOnClickListener {
                    startActivity(Intent(this, Authorization::class.java))
                    finish()
                }
            } else {
                bindingAbout.headline.text = resources.getString(R.string.next)
            }
        })
    }

    override fun onBackPressed() {
        if (viewPager2.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager2.currentItem = viewPager2.currentItem - 1
        }
    }
}