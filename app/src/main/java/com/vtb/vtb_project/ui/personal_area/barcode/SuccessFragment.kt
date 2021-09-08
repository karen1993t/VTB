package com.vtb.vtb_project.ui.personal_area.barcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vtb.vtb_project.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment() {
    private var bindingSuccessFragment: FragmentSuccessBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSuccessFragment = FragmentSuccessBinding.inflate(inflater)

        val safeArgs:SuccessFragmentArgs by navArgs()
        val code = safeArgs.code

        bindingSuccessFragment?.fragmentSuccessTextViewCode?.text = code

        bindingSuccessFragment?.fragmentSuccessButtonBackToScanner?.setOnClickListener {
            findNavController().navigateUp()
        }

        return bindingSuccessFragment?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingSuccessFragment = null
    }
}