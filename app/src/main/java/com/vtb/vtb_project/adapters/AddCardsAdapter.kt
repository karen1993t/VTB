package com.vtb.vtb_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vtb.vtb_project.R
import com.vtb.vtb_project.ui.personal_area.ModelBalanceUp

class AddCardsAdapter(val context: Context, private val listCards:MutableList<ModelBalanceUp>): RecyclerView.Adapter<AddCardsAdapter.ViewHolderAddedCards>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAddedCards {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_balance_up,parent,false)
    return ViewHolderAddedCards(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAddedCards, position: Int) {
        val currentList = listCards[position]
       holder.imageLogo.setImageResource(currentList.imgLogo)
        holder.nameCard.text = currentList.cardName
        holder.numberCard.text = currentList.cardNumber
        holder.balanceCard.text = currentList.cardBalance
    }

    override fun getItemCount(): Int {
       return listCards.size
    }

    class ViewHolderAddedCards(view:View):RecyclerView.ViewHolder(view) {
        val imageLogo: ImageView =view.findViewById(R.id.logo_card)
        val nameCard: TextView =view.findViewById(R.id.name_card)
        val numberCard: TextView =view.findViewById(R.id.numbers_card)
        val balanceCard: TextView =view.findViewById(R.id.balance_card)

    }
}