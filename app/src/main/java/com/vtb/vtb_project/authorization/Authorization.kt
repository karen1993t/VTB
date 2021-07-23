package com.vtb.vtb_project.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vtb.vtb_project.databinding.ActivityAuthorizationBinding

class Authorization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingAuth = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(bindingAuth.root)


    }
}
