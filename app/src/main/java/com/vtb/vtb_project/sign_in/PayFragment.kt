package com.vtb.vtb_project.sign_in

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentPayBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PayFragment : Fragment() {
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraExecutor: ExecutorService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val previewView = view?.findViewById<PreviewView>(R.id.preview_view)

        cameraExecutor = Executors.newSingleThreadExecutor()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireContext())

        @SuppressLint("UnsafeExperimentalUsageError")
        fun bindPreview(cameraProvider: ProcessCameraProvider) {
            val preview: Preview = Preview.Builder()
                .build()
            val cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()
            preview.setSurfaceProvider(previewView?.surfaceProvider)

            cameraProvider.bindToLifecycle(
                this as LifecycleOwner,
                cameraSelector,
                preview
            )
        }

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))
        return inflater.inflate(R.layout.fragment_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingPayFragment = FragmentPayBinding.bind(view)

//        fun checkCameraPermission() {
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.CAMERA
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                Intent().also {
//                    it.action = BassBoost.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                    it.data = Uri.fromParts("package", packageName, null)
//                    startActivity(it)
//                    finish()
//                }
//            }
//        }


    }

}