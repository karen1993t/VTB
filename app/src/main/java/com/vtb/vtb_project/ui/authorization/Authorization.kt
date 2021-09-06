package com.vtb.vtb_project.ui.authorization

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtb_project.ui.create_account_and_visa_card.ShowAuthorizationActivity
import com.vtb.vtb_project.databinding.ActivityAuthorizationBinding
import com.vtb.vtb_project.view_model.ViewModelAuthorization

class Authorization : AppCompatActivity() {

   private var bindingAuth: ActivityAuthorizationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAuth = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(bindingAuth?.root)


        val liveDataAuthorization = ViewModelProvider(this).get(ViewModelAuthorization::class.java)

        liveDataAuthorization.authorizationLiveData.observe(this, {
            if (it) {
                finish()
            }
        })


    }

    override fun onBackPressed() {
        Intent(this, ShowAuthorizationActivity::class.java)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingAuth = null
    }
}
