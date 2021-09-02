package com.vtb.vtb_project.ui.personal_area

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.AddCardsAdapter
import com.vtb.vtb_project.databinding.FragmentAddedCardsBinding
import com.vtb.vtb_project.view_model.ViewModelPersonalArea

class AddedCardsFragment : Fragment() {
    private lateinit var bindingAddedCardsFragment: FragmentAddedCardsBinding
    private val liveDataBtnDone: ViewModelPersonalArea by activityViewModels()
    private val liveDataSetNumberCard: ViewModelPersonalArea by activityViewModels()
    private val liveDataSetNameCard: ViewModelPersonalArea by activityViewModels()
    private  var count:Int= 0
    private  var nameCard:String = ""

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

       liveDataSetNameCard.getNameLiveData.observe(viewLifecycleOwner, {
            Log.d("log",it)
           nameCard =it
        })


        val listCard = mutableListOf(
            ModelBalanceUp(R.drawable.logo_master, "MasterCard", "4555", "121 234, 94 ₽")
        )
        val adapter = AddCardsAdapter(requireContext(), listCard)
        recyclerAddCards.adapter = adapter
        recyclerAddCards.layoutManager = LinearLayoutManager(requireContext())

        liveDataBtnDone.addCardLiveData.observe(viewLifecycleOwner, {
            count++

            if (it) {
                liveDataSetNumberCard.getNumberLiveData.observe(viewLifecycleOwner,{it
                listCard.add(
                    count,
                    ModelBalanceUp(
                        R.drawable.logo_master,
                        nameCard
                        ,
                        it.substring(15,19),
                        "dfsd"
                    )
                )
                })
            }

        })


    }
}