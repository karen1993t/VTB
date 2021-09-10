package com.vtb.vtb_project.ui.open_vtb_card_steps.face_detect

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import com.vtb.vtb_project.ui.open_vtb_card_steps.FaceDetectListener

class FaceDetectAnalyzer(private val isFaceDetect: FaceDetectListener) : ImageAnalysis.Analyzer {
    // creating face detect options
    private val optionsFaceDetect = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
        .enableTracking()
        .build()
    private val faceDetectScanner = FaceDetection.getClient(optionsFaceDetect)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val inputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)


            faceDetectScanner.process(inputImage).addOnSuccessListener { faceDetect ->
                Log.d("test_1", "ok")
                isFaceDetect(false)
                for (face in faceDetect) {
                    isFaceDetect(true)

                    if (face.leftEyeOpenProbability == 1.0.toFloat()) {
                        val leftEyeOpenProb = face.leftEyeOpenProbability
                        Log.d("test_1", "leftEyarOpen = ${leftEyeOpenProb}")
                    }
                    Log.d("test_1", "${face.getLandmark(FaceLandmark.LEFT_EAR)}")
                    Log.d("test_1", "${face.boundingBox}")
                }
            }
                .addOnFailureListener {

                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}