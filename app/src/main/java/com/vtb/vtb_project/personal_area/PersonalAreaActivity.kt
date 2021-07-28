package com.vtb.vtb_project.personal_area

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.PersonalAreaAdapter
import com.vtb.vtb_project.databinding.ActivityPersonalAreaBinding

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


        val spannable = SpannableStringBuilder(bindingPersonalArea.titleMoney.text)
        spannable.setSpan(
            ForegroundColorSpan(Color.GRAY), 6, 13,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        bindingPersonalArea.titleMoney.text = spannable

//        bindingPersonalArea.pay.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_payFragment)
//        }
//        bindingPersonalArea.replenish.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_balanceUpFragment)
//        }


        val recycler = findViewById<RecyclerView>(R.id.recycler_view)
        val listData = listOf(
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "10:00", "-1 296 288 ₽"),

            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "10:00", "-1 296 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "10:00", "-1 296 288 ₽"),
        )
        val customAdapter = PersonalAreaAdapter(this, listData)
        recycler.adapter = customAdapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personal_area, menu)
        return true
    }

}
