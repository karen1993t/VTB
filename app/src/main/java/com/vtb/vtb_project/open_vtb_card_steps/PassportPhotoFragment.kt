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
import com.vtb.vtb_project.databinding.FragmentPassportPhotoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PassportPhotoFragment : Fragment() {
    lateinit var showBinding: FragmentPassportPhotoBinding
    private lateinit var currentPhotoPath: String
    private lateinit var uriPhotoPassport: Uri
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uriPhotoPassport = Uri.parse("")
        showBinding = FragmentPassportPhotoBinding.inflate(inflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBinding.btnClose.setOnClickListener {
            Navigation.findNavController(showBinding.root)
                .navigate(R.id.action_passportPhotoFragment_to_home)
        }

        val capturePhotoPassport =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { successFull ->
                if (successFull) Navigation.findNavController(showBinding.root)
                    .navigate(R.id.action_go_to_passportPhotoSuccessFragment)
                else Navigation.findNavController(showBinding.root)
                    .navigate(R.id.action_go_to_bankAuthorizationRejectFragment)
            }
        val requestPermissionPicture =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    uriPhotoPassport = FileProvider.getUriForFile(
                        requireContext(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        createPhotoFile()
                    )
                    capturePhotoPassport.launch(uriPhotoPassport)
                }
            }

        showBinding.btnGoToCapturePassportPhoto.setOnClickListener {
            requestPermissionPicture.launch(Manifest.permission.CAMERA)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createPhotoFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HH mm ss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "Photo_Passport_${timeStamp}",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
}