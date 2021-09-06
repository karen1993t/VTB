package com.vtb.vtb_project.ui.personal_area

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.vtb.vtb_project.databinding.FragmentPayBinding
import kotlinx.android.synthetic.main.recycler_row.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PayFragment() : Fragment() {
    private var bindingPayFragment: FragmentPayBinding? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingPayFragment = FragmentPayBinding.inflate(inflater)
        return bindingPayFragment?.root
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
                if (isPermissionCameraGranted && isPermissionAudioGranted) {
                    startCamera()
                }
            }
        requestPermission.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )
        )


    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()


            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(
                    bindingPayFragment?.previewQr?.surfaceProvider
                )
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()



            imageAnalysis.setAnalyzer(cameraExecutor,YourImageAnalyzer() )

            cameraProvider.bindToLifecycle(
                this as LifecycleOwner,
                cameraSelector,
                imageAnalysis,
                preview
            )



            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,



                    // -> can not open camera, imageCapture -> ok!

                )
            } catch (e: Exception) {
                Log.e("PreviewUseCase", "Binding failed! :(", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))


    }


    override fun onDestroy() {
        super.onDestroy()
        bindingPayFragment = null
    }


}