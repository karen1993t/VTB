package com.vtb.vtb_project.ui.create_account_and_visa_card

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentShowAuthorizationCardHolderBinding
import com.vtb.vtb_project.ui.open_vtb_card_steps.OpenVtbCardStepsActivity

class ShowAuthorizationCardHolderFragment : Fragment() {
    private lateinit var bindingShow: FragmentShowAuthorizationCardHolderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_authorization_card_holder, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingShow = FragmentShowAuthorizationCardHolderBinding.bind(view)
        bindingShow.btnLogin.setOnClickListener {
            val intent = Intent(context, OpenVtbCardStepsActivity::class.java)
            startActivity(intent)
        }
    }
}