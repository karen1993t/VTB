package com.vtb.vtb_project.ui.create_account_and_visa_card

import android.annotation.SuppressLint

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentShowAuthorizedNoCardBinding
import com.vtb.vtb_project.ui.on_boarding_about_fragments.OnBoardingAbout

class ShowAuthorizationNoCardFragment : Fragment() {
    private lateinit var bindingShow: FragmentShowAuthorizedNoCardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_authorized_no_card, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingShow = FragmentShowAuthorizedNoCardBinding.bind(view)
        bindingShow.btnGetVirtualCard.setOnClickListener {
            val intent = Intent(context, OnBoardingAbout::class.java)
            startActivity(intent)
        }

    }

  }