package com.vtb.vtb_project.ui.open_vtb_card_steps


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentDetectVideoSubmitBinding
import com.vtb.vtb_project.view_model.SharedCardStepsViewModel

class DetectVideoSubmitFragment : Fragment(), View.OnClickListener {
    private val sharedViewModel: SharedCardStepsViewModel by activityViewModels()
    private var showBindingSubmit: FragmentDetectVideoSubmitBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBindingSubmit = FragmentDetectVideoSubmitBinding.inflate(inflater)
        return showBindingSubmit?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videView = showBindingSubmit?.videoViewSubmit

        sharedViewModel.videoDetectUri.observe(viewLifecycleOwner, { uriVideoDetect ->
            showBindingSubmit?.videoViewSubmit?.setVideoURI(uriVideoDetect)

            videView?.setMediaController(android.widget.MediaController(requireContext()))
            videView?.requestFocus(0)
            videView?.start()
        })

        showBindingSubmit?.btnSubmit?.setOnClickListener(this)
        showBindingSubmit?.btnRetake?.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            showBindingSubmit?.btnSubmit -> showBindingSubmit?.root?.let {
                Navigation.findNavController(
                    it
                ).navigate(R.id.action_go_to_biometrySuccessFragment)
            }
            showBindingSubmit?.btnRetake -> showBindingSubmit?.root?.let {
                Navigation.findNavController(it).navigate(R.id.action_go_to_faceDetectCamera)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showBindingSubmit = null
    }
}