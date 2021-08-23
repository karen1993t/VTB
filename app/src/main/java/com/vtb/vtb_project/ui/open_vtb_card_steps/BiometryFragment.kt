package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R

import com.vtb.vtb_project.databinding.FragmentBiometryBinding


class BiometryFragment : Fragment() {

    var showBinding: FragmentBiometryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBinding = FragmentBiometryBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBinding?.btnGoToBiometricsScanner?.setOnClickListener {
            showBinding?.root?.let { view ->
                Navigation.findNavController(view).navigate(R.id.action_go_to_biometryVideoFragment)
            }
        }
        showBinding?.btnClose?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_biometryFragment_to_showVtbCardThreeStepsFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showBinding = null
    }
}



