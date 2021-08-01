package com.vtb.vtb_project.personal_area

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentBalanceUpBinding
import com.vtb.vtb_project.tools.ToolsForEditText

class BalanceUpFragment : Fragment() {
 private lateinit var bindingBalanceUpFragment:FragmentBalanceUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingBalanceUpFragment = FragmentBalanceUpBinding.inflate(inflater)
        return bindingBalanceUpFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingBalanceUpFragment.addCard.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_balanceUpFragment_to_addCardFragment)
        }
        val array = arrayOf("___ ___ ___")
        ToolsForEditText.createMaskEdit(0,array,bindingBalanceUpFragment.editAmount)

        bindingBalanceUpFragment.addedCards.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_balanceUpFragment_to_addedCardsFragment)
        }
    }


}