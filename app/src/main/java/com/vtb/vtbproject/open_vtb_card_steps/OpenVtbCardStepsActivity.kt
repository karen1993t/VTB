package com.vtb.vtbproject.open_vtb_card_steps


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtbproject.R

class OpenVtbCardStepsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_open_vtb_card_steps)
        val viewModel = ViewModelProvider(this).get(SharedViewModel()::class.java)

    }
}