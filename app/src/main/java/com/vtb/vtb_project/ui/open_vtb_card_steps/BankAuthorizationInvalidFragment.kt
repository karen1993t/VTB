package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vtb.vtb_project.databinding.FragmentBankAuthInvalidBinding


class BankAuthorizationInvalidFragment : Fragment() {
    var showBinding: FragmentBankAuthInvalidBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBinding = FragmentBankAuthInvalidBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        showBinding = null
    }
}