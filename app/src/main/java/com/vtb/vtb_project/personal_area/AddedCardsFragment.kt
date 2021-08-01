package com.vtb.vtb_project.personal_area

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.AddCardsAdapter
import com.vtb.vtb_project.databinding.FragmentAddedCardsBinding

class AddedCardsFragment : Fragment() {
    private lateinit var bindingAddedCardsFragment: FragmentAddedCardsBinding
    private val liveDataBtnDone:ViewModelPersonalArea by activityViewModels()
    private val liveDataSetNumberCard:ViewModelPersonalArea by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingAddedCardsFragment = FragmentAddedCardsBinding.inflate(inflater)
        return bindingAddedCardsFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerAddCards = bindingAddedCardsFragment.containerAddedCards
        val listCard = mutableListOf(
            ModelBalanceUp(R.drawable.logo_master,"MasterCard","4555","121 234, 94 ₽")
        )
      val adapter = AddCardsAdapter(requireContext(),listCard)
        recyclerAddCards.adapter = adapter
        recyclerAddCards.layoutManager = LinearLayoutManager(requireContext())

        liveDataBtnDone.addCardLiveData.observe(viewLifecycleOwner,{

            if (it){
                listCard.add(0,
                    ModelBalanceUp(R.drawable.logo_master,liveDataSetNumberCard.getNumberLiveData.toString(),"fgfgdf","dfsd")
                )
            }

        })



    }
}