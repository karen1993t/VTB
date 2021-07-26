package com.vtb.vtb_project.on_boarding_about_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vtb.vtb_project.R
import java.security.Provider


class AboutSixFragment : Fragment() {
    val boolean:Boolean = false
    private val viewModelOnStart :ViewModelOnBoarding by activityViewModels()
    val viewModelOnPause :ViewModelOnBoarding by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_six, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModelOnStart.runFragmentSix(true)


    }

    override fun onPause() {
        super.onPause()
        viewModelOnPause.runFragmentSix(false)
    }

}