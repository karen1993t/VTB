package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.vtb.vtb_project.BuildConfig
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.vtb.vtb_project.databinding.FragmentFaceDetectVideoBinding
import com.vtb.vtb_project.ui.open_vtb_card_steps.face_detect.FaceDetectAnalyzer
import java.text.SimpleDateFormat
import java.io.File
import java.util.Date
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


typealias FaceDetectListener = (isDetect: Boolean) -> Unit


class FaceDetectVideoFragment : Fragment() {
    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }

    private lateinit var currentVideoPath: String
    private lateinit var uri: Uri
    private var showBindingCamera: FragmentFaceDetectVideoBinding? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGrantedMap ->
                var isPermissionCameraGranted = false
                var isPermissionAudioGranted = false
                for (entry in isGrantedMap) {
                    when (entry.key) {
                        Manifest.permission.CAMERA -> {
                            isPermissionCameraGranted = entry.value
                        }
                        Manifest.permission.RECORD_AUDIO -> {
                            isPermissionAudioGranted = entry.value
                        }
                        else -> Toast.makeText(
                            requireContext(),
                            "Permissions not granted by the user.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                if (isPermissionCameraGranted && isPermissionAudioGranted) {   // testing
                    startCamera()
                }
            }
        requestPermission.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )
        )
        showBindingCamera?.cameraCaptureButton?.setOnClickListener {
            takeVideo()
        }

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(
                    showBindingCamera?.scanFacePreviewView?.surfaceProvider
                )
            }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, FaceDetectAnalyzer { isFaceDetect ->
                        Log.d("test_1", isFaceDetect.toString())
                    })
                }
            videoCapture = VideoCapture.Builder().build()
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageAnalysis,
                    videoCapture                       // -> can not open camera, imageCapture -> ok!

                )
            } catch (e: Exception) {
                Log.e("PreviewUseCase", "Binding failed! :(", e)
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
                    uri = FileProvider.getUriForFile(
                        requireContext(),
                        BuildConfig.APPLICATION_ID + ".provider", videoFile
                    )
                    val msg = "Video capture succeeded"
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }

                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            })
    }


    @SuppressLint("SimpleDateFormat")
    fun createVideoFile(): File {
        val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT).format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        return File.createTempFile(
            "Biometric_Video_${timeStamp}",
            ".mp4",
            storageDir
        ).apply {
            currentVideoPath = absolutePath
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showBindingCamera = null
    }
}