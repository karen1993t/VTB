package com.vtb.vtb_project.sign_in

import android.graphics.Color
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.PersonalAreaAdapter
import com.vtb.vtb_project.databinding.FragmentPersonalAreaBinding

class PersonalAreaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_personal_area, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingPersonalArea = FragmentPersonalAreaBinding.bind(view)

        val spannable = SpannableStringBuilder(bindingPersonalArea.titleMoney.text)
        spannable.setSpan(ForegroundColorSpan(Color.GRAY), 6, 13,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE)


        val recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
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
        val customAdapter = PersonalAreaAdapter(view.context, listData)
        recycler.adapter = customAdapter
        recycler.layoutManager = LinearLayoutManager(activity)

    }
}