package com.vtb.vtb_project.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentSendEmailAuthorizationBinding

class AuthorizationFragmentSendEmail : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_email_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingSendEmail= FragmentSendEmailAuthorizationBinding.bind(view)
        bindingSendEmail.iconCloseFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_authorizationFragmentSendEmail_to_authorizationFirstFragment)
        }


    }



}