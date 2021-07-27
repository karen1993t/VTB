package com.vtb.vtb_project.adapters

import android.content.Context
import android.preference.PreferenceActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.vtb.vtb_project.R
import com.vtb.vtb_project.sign_in.ModelPersonalArea

class PersonalAreaAdapter(val context: Context, val listPersonalArea: List<ModelPersonalArea>) :
    Adapter<PersonalAreaAdapter.ViewHolderPersonalArea>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersonalArea {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false)
        return ViewHolderPersonalArea(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPersonalArea, position: Int) {

        holder.image.setImageResource(listPersonalArea[position].image)
        holder.titleText.text = listPersonalArea[position].title
        holder.timeText.text = listPersonalArea[position].time
        holder.priceText.text = listPersonalArea[position].price


    }


    override fun getItemCount(): Int {
        return listPersonalArea.size
    }


    class ViewHolderPersonalArea(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val titleText = view.findViewById<TextView>(R.id.title)
        val timeText = view.findViewById<TextView>(R.id.time)
        val priceText = view.findViewById<TextView>(R.id.price)

    }
}