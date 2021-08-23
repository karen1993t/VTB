package com.vtb.vtb_project.authorization

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtb_project.ui.create_account_and_visa_card.ShowAuthorizationActivity
import com.vtb.vtb_project.databinding.ActivityAuthorizationBinding

class Authorization : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingAuth = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(bindingAuth.root)


        val liveDataAuthorization= ViewModelProvider(this).get(ViewModelAuthorization::class.java)

        liveDataAuthorization.authorizationLiveData.observe(this, {
           if (it){
               finish()
           }
        })


    }

    override fun onBackPressed() {
        Intent(this,ShowAuthorizationActivity::class.java)
        finish()
    }
}
