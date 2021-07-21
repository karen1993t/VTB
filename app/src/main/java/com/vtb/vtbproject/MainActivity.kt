package com.vtb.vtbproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.vtb.vtbproject.create_account_and_visa_card.ShowAuthorizationActivity
import com.vtb.vtbproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val bindingMain = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingMain.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val intentShowAuthorization = Intent(this, ShowAuthorizationActivity::class.java)
        bindingMain.headLineEmail.setOnClickListener() {
            startActivity(intentShowAuthorization)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return  true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}