package com.vtb.vtb_project.ui.open_vtb_card_steps


import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.BuildConfig
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentBiometryVideoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class BiometryVideoFragment : Fragment() {

    var showBinding: FragmentBiometryVideoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        showBinding = FragmentBiometryVideoBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBinding?.btnGoToBiometricsScanner?.setOnClickListener {
            Navigation.findNavController(view)
                        .navigate(R.id.action_go_to_faceDetectCamera)
        }
    }
}