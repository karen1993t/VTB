package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.core.impl.VideoCaptureConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.vtb.vtb_project.BuildConfig
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentFaceDetectVideoBinding
import com.vtb.vtb_project.ui.open_vtb_card_steps.face_detect.FaceDetectAnalyzer
import com.vtb.vtb_project.view_model.SharedCardStepsViewModel
import java.text.SimpleDateFormat
import java.io.File
import java.util.Date
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


typealias FaceDetectListener = (isDetect: Boolean) -> Unit


class FaceDetectVideoFragment : Fragment(), View.OnClickListener {
    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }

    private lateinit var currentVideoPath: String
    private lateinit var uri: Uri
    private var showBindingCamera: FragmentFaceDetectVideoBinding? = null
    private val sharedViewModel: SharedCardStepsViewModel by activityViewModels()
    private var videoCapture: VideoCapture? = null
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var videoFile: File

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
        showBindingCamera = FragmentFaceDetectVideoBinding.inflate(inflater)
        return showBindingCamera?.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGrantedMap ->
                var isPermissionCameraGranted = false
                var isPermissionAudioGranted = false
                var isPermissionWriteExternalStorageGranted = false
                var isPermissionReadExternalStorageGranted = false
                for (entry in isGrantedMap) {
                    when (entry.key) {
                        Manifest.permission.CAMERA -> {
                            isPermissionCameraGranted = entry.value
                        }
                        Manifest.permission.RECORD_AUDIO -> {
                            isPermissionAudioGranted = entry.value
                        }

                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                            isPermissionWriteExternalStorageGranted = entry.value
                        }
                        Manifest.permission.READ_EXTERNAL_STORAGE -> {
                            isPermissionReadExternalStorageGranted = entry.value
                        }
                    }
                }
                if (isPermissionCameraGranted && isPermissionAudioGranted &&
                    isPermissionReadExternalStorageGranted && isPermissionWriteExternalStorageGranted
                ) startCamera()
                else Toast.makeText(
                    requireContext(), "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        requestPermission.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,

                )
        )
        showBindingCamera?.cameraCaptureButton?.setOnClickListener(this)
        showBindingCamera?.btnBack?.setOnClickListener(this)
        showBindingCamera?.btnClose?.setOnClickListener(this)


    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(
                    showBindingCamera?.scanFacePreviewView?.surfaceProvider
                )
            }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setTargetResolution(Size(1280, 720))
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, FaceDetectAnalyzer { isFaceDetect ->

                    })
                }

            videoCapture = VideoCapture.Builder().build()
            imageCapture = ImageCapture.Builder()

                .build()



            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    cameraSelector,
                    preview,

                    videoCapture               // -> can not open camera, imageCapture -> ok!

                )
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Filed Camera !", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    @SuppressLint("RestrictedApi")
    private fun takeVideo() {

        val videoCapture = videoCapture ?: return
        videoFile = createVideoFile()

        val outputOptions = VideoCapture.OutputFileOptions.Builder(videoFile).build()
        videoCapture.startRecording(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : VideoCapture.OnVideoSavedCallback {
                override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                    try {
                        uri = FileProvider.getUriForFile(
                            requireContext(),
                            BuildConfig.APPLICATION_ID + ".provider", videoFile
                        )
                        sharedViewModel.setUriVideoDetect(uri)
                        showBindingCamera?.root?.let { view ->
                            Navigation.findNavController(view)
                                .navigate(R.id.action_go_to_detectVideoSubmitFragment)
                        }

                    } catch (e: Exception) {

                    }
                }

                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {

                    showBindingCamera?.root?.let {
                        Navigation.findNavController(it)
                            .navigate(R.id.action_go_to_failureBiometryFragment)
                    }
                }
            })
        showBindingCamera?.cameraCaptureButton?.setOnClickListener {
            videoCapture.stopRecording()

        }

    }


    @SuppressLint("SimpleDateFormat")
    fun createVideoFile(): File {
        val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT).format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        return File.createTempFile(
            "Biometric_Video_${timeStamp}",
            ".3gp",
            storageDir
        ).apply {
            currentVideoPath = absolutePath
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(view: View?) {
        when (view) {
            showBindingCamera?.cameraCaptureButton -> {
                takeVideo()
                showBindingCamera?.cameraCaptureButton?.background =
                    resources.getDrawable(R.drawable.ic_background_stop_video_button, null)
            }
            showBindingCamera?.btnBack -> showBindingCamera?.root?.let {
                Navigation.findNavController(it).navigate(R.id.action_go_to_biometryVideoFragment)
            }
            showBindingCamera?.btnClose -> showBindingCamera?.root?.let {
                Navigation.findNavController(it).navigate(R.id.action_go_to_biometryFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showBindingCamera = null
    }
}