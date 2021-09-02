package com.vtb.vtb_project.ui.on_boarding_about_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vtb.vtb_project.R
import com.vtb.vtb_project.view_model.ViewModelOnBoarding

class AboutOneFragment : Fragment() {
    private val viewModelClose: ViewModelOnBoarding by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_one, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModelClose.closeOnBoardingAbout(true)
    }
}