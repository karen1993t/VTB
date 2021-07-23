package com.vtb.vtb_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vtb.vtb_project.create_account_and_visa_card.ShowAuthorizationCardHolderFragment


class AuthorizationViewPagerAdapter(
    fragment: Fragment,   // add to fragment lists
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentImpute = fragment
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragmentImpute
            1 -> ShowAuthorizationCardHolderFragment() // deleting

            else -> Fragment()
        }
    }
}