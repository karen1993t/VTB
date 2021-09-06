package com.vtb.vtb_project.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentSendEmailAuthorizationBinding

class AuthorizationFragmentSendEmail : Fragment() {

  private var bindingSendEmail: FragmentSendEmailAuthorizationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSendEmail= FragmentSendEmailAuthorizationBinding.inflate(inflater)
        return bindingSendEmail?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingSendEmail?.iconCloseFragment?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_authorizationFragmentSendEmail_to_authorizationFirstFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingSendEmail= null
    }
}