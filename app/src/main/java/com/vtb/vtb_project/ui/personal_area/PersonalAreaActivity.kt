package com.vtb.vtb_project.ui.personal_area

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.ActivityPersonalAreaBinding
import com.vtb.vtb_project.view_model.ViewModelPersonalArea


class PersonalAreaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingPersonalArea = ActivityPersonalAreaBinding.inflate(layoutInflater)
        setContentView(bindingPersonalArea.root)

        supportActionBar?.apply {
            title = resources.getString(R.string.personal_area_vtb)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        Log.d("logs","cretted")

        val liveDataPersonalArea = ViewModelProvider(this).get(ViewModelPersonalArea::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personal_area, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

