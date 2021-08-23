package com.vtb.vtb_project.ui.open_vtb_card_steps


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtb_project.R
import com.vtb.vtb_project.view_model.SharedCardStepsViewModel

class OpenVtbCardStepsActivity : AppCompatActivity() {

    private lateinit var cardStepsViewModel: SharedCardStepsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_open_vtb_card_steps)
        cardStepsViewModel = ViewModelProvider(this).get(SharedCardStepsViewModel()::class.java)
        cardStepsViewModel.isCloseActivityLiveData.observe(this, {
            if (it) {
                finish()
            }
        })
    }
}