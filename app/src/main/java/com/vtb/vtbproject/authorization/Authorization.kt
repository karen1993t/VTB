package com.vtb.vtbproject.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vtb.vtbproject.databinding.ActivityAuthorizationBinding

class Authorization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingAuth = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(bindingAuth.root)


    }
}
