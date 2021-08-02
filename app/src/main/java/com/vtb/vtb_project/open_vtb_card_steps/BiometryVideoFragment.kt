package com.vtb.vtb_project.open_vtb_card_steps


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
    private lateinit var currentVideoPath: String
    private lateinit var uri: Uri                                        // video biometry
    private lateinit var showBinding: FragmentBiometryVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uri = Uri.parse("")
        showBinding = FragmentBiometryVideoBinding.inflate(inflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoLauncher =
            registerForActivityResult(ActivityResultContracts.CaptureVideo()) { successFull ->
                if (successFull) {

                    Navigation.findNavController(showBinding.root)
                        .navigate(R.id.action_go_to_biometrySuccessFragment)
                } else Navigation.findNavController(showBinding.root)
                    .navigate(R.id.action_go_to_failureBiometryFragment)

            }
        val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    uri = FileProvider.getUriForFile(
                        requireContext(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        createVideoFile()
                    )
                    videoLauncher.launch(uri)
                }
            }
        showBinding.btnGoToBiometricsScanner.setOnClickListener {
            requestPermission.launch(Manifest.permission.CAMERA)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createVideoFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HH mm ss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        return File.createTempFile(
            "Biometric_${timeStamp}",
            ".mp4",
            storageDir
        ).apply {
            currentVideoPath = absolutePath
        }
    }

}