package com.vtb.vtb_project.on_boarding_about_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vtb.vtb_project.R

class AboutTreeFragment : Fragment() {
    private val viewModelClose: ViewModelOnBoarding by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_tree, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModelClose.closeOnBoardingAbout(true)
    }
}