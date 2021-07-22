package com.vtb.vtbproject.open_vtb_card_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtbproject.R
import com.vtb.vtbproject.databinding.FragmentLegalAddressBinding

class LegalAddressFragment : Fragment() {
   private lateinit var showBinding:FragmentLegalAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBinding = FragmentLegalAddressBinding.inflate(inflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBinding.btnClose.setOnClickListener{
            Navigation.findNavController(showBinding.root).navigate(R.id.action_legalAddressFragment_go_to_home)
        }
        showBinding.btnGoToIdentityVerificationFragment.setOnClickListener{
            TODO("go to next fragment")
        }


    }
}