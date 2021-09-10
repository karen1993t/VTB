package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentContractBinding

class ContractFragment : Fragment() {
 var showBinding: FragmentContractBinding?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBinding = FragmentContractBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBinding?.btnAccept?.setOnClickListener {
             showBinding?.root?.let{
                 Navigation.findNavController(it).navigate(R.id.action_go_to_enterEmailFragment)
             }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showBinding = null
    }
}