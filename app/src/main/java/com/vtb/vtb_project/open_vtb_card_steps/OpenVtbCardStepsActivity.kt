package com.vtb.vtb_project.open_vtb_card_steps


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtb_project.R

class OpenVtbCardStepsActivity : AppCompatActivity() {

private lateinit var   viewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_open_vtb_card_steps)
         viewModel = ViewModelProvider(this).get(SharedViewModel()::class.java)
        viewModel.isCloseActivityLiveData.observe(this, Observer {
            if (it){
                finish()
            }
        })
    }
}