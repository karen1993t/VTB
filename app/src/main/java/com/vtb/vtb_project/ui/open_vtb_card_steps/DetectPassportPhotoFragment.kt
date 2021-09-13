package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.BuildConfig
import com.vtb.vtb_project.R
import com.vtb.vtb_project.analyzer.FaceDetectAnalyzer
import com.vtb.vtb_project.analyzer.PassportDetectAnalyzer
import com.vtb.vtb_project.databinding.FragmentDetectPassportPhotoBinding
import com.vtb.vtb_project.tools.Constants
import com.vtb.vtb_project.view_model.SharedCardStepsViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DetectPassportPhotoFragment : Fragment() {
    private var showDetectPassportBinding: FragmentDetectPassportPhotoBinding? = null
    private lateinit var currentVideoPath: String
    private lateinit var uri: Uri
    private val sharedViewModel: SharedCardStepsViewModel by activityViewModels()
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var passportPhotoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uri = Uri.parse("")
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showDetectPassportBinding = FragmentDetectPassportPhotoBinding.inflate(inflater)
        return showDetectPassportBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGrantedMap ->


                for (entry in isGrantedMap) {
                    when (entry.key) {
                        Manifest.permission.CAMERA -> {
                            if (entry.value) startCamera()
                        }

                        else -> Toast.makeText(
                            requireContext(), "Permissions not granted by the user.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        requestPermission.launch(
            arrayOf(
                Manifest.permission.CAMERA,
            )
        )
        showDetectPassportBinding?.cameraCaptureButton?.setOnClickListener {
            takePhoto()
        }
        showDetectPassportBinding?.btnBack?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_detectPassportPhoto_to_passportPhotoFragment)
        }
        showDetectPassportBinding?.btnClose?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_detectPassportPhoto_to_showVtbCardThreeStepsFragment)
        }

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(
                    showDetectPassportBinding?.scanFacePreviewView?.surfaceProvider
                )
            }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)

                .build()
                .also {
                    it.setAnalyzer(cameraExecutor,  PassportDetectAnalyzer())
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis,
                    imageCapture               // -> can not open camera, imageCapture -> ok!

                )
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Filed Camera !", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {

        val imageCapture = imageCapture ?: return
        passportPhotoFile = createPassportFile()
        Log.d("photo", "takePictures")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(passportPhotoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Log.d("photo", "capturedd")

                    uri = FileProvider.getUriForFile(
                        requireContext(),
                        BuildConfig.APPLICATION_ID + ".provider", passportPhotoFile
                    )
                    sharedViewModel.setUriPassportPhoto(uri)
                    showDetectPassportBinding?.root?.let { view ->
                        Navigation.findNavController(view)
                            .navigate(R.id.action_go_to_detectPassportPhotoSubmitFragment)
                    }


                }

                override fun onError(exception: ImageCaptureException) {
                    Log.d("photo", "error")
                }

            })

    }

    @SuppressLint("SimpleDateFormat")
    fun createPassportFile(): File {
        val timeStamp: String = SimpleDateFormat(Constants.FILENAME_DATE_FORMAT).format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "Biometric_Passport_${timeStamp}",
            ".jpg",
            storageDir
        ).apply {
            currentVideoPath = absolutePath
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        showDetectPassportBinding = null
    }
}