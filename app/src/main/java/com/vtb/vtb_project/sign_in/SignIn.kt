package com.vtb.vtb_project.sign_in

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtb_project.R

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val viewModelSignIn = ViewModelProvider(this).get(ViewModelSignIn::class.java)

        viewModelSignIn.closeSignInLiveData.observe(this, {
            if (it) {
                finish()
            }
        })
    }
}