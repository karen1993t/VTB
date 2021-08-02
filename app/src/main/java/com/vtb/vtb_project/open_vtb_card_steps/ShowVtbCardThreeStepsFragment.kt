package com.vtb.vtb_project.open_vtb_card_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentOpenVtbCardThreeStepsBinding

class ShowVtbCardThreeStepsFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var showBinding: FragmentOpenVtbCardThreeStepsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBinding = FragmentOpenVtbCardThreeStepsBinding.inflate(layoutInflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBinding.btnGetStarted.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_goToPersonal_information)

        }
        showBinding.step1.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_goToPersonal_information)
        }
        showBinding.btnClose.setOnClickListener {
            sharedViewModel.closeActivity(true)
        }
        showBinding.step2.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_go_to_biometryFragment)
        }
        showBinding.step3.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_showVtbCardThreeStepsFragment_to_passportPhotoFragment)
        }
    }
}