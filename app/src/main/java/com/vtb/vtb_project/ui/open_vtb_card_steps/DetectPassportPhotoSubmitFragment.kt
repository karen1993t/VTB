package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentDetectPassportPhotoSubmitBinding
import com.vtb.vtb_project.view_model.SharedCardStepsViewModel

class DetectPassportPhotoSubmitFragment : Fragment() {
    private var showBindingPassportSubmit: FragmentDetectPassportPhotoSubmitBinding? = null
    private val sharedViewModel: SharedCardStepsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBindingPassportSubmit = FragmentDetectPassportPhotoSubmitBinding.inflate(inflater)
        return showBindingPassportSubmit?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.passportPhotoDetectUri.observe(viewLifecycleOwner, { uriPassportPhoto ->
            showBindingPassportSubmit?.passportImageView?.setImageURI(uriPassportPhoto)
        })
        showBindingPassportSubmit?.btnRetake?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_detectPassportPhotoSubmitFragment_to_detectPassportPhoto)

        }
        showBindingPassportSubmit?.btnSubmit?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_detectPassportPhotoSubmitFragment_to_passportPhotoSuccessFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        showBindingPassportSubmit = null
    }
}