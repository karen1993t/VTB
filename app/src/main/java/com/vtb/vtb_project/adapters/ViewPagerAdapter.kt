package com.vtb.vtb_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vtb.vtb_project.ui.on_boarding_about_fragments.*

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val listAboutFragments = arrayListOf(
        AboutOneFragment(),
        AboutTwoFragment(),
        AboutTreeFragment(),
        AboutFourFragment(),
        AboutFiveFragment(),
        AboutSixFragment()
    )

    override fun getItemCount(): Int {
        return listAboutFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return listAboutFragments[position]
        }
    }
