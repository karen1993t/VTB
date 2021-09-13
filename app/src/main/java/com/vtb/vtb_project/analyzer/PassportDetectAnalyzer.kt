package com.vtb.vtb_project.analyzer

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class PassportDetectAnalyzer() : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
val listPassportCheckName = mutableListOf<String>("PASSPORT","REPUBLIC OF","COUNTRY CODE","<<<<<")
            val result = recognizer.process(image).addOnSuccessListener {

                for (block in it.textBlocks){
                    val blockText = block.text
                    val blockCornerPoints = block.cornerPoints
                    val blockFrame = block.boundingBox


                    for (line in block.lines) {
                        val lineText = line.text
                        val lineCornerPoints = line.cornerPoints
                        val lineFrame = line.boundingBox

                        if(listPassportCheckName.contains(lineText)){
                            Log.d("lineText"," lineText = ${ lineText }\n")
                        }


                        for (element in line.elements) {
                            val elementText = element.text
                            val elementCornerPoints = element.cornerPoints
                            val elementFrame = element.boundingBox


                        }
                    }
                }


            }
                .addOnFailureListener{
                    Log.d("textDetect","error")
                }
                .addOnCompleteListener{

                    imageProxy.close()

                }
        }
    }
}