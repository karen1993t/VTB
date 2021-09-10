package com.vtb.vtb_project.ui.create_account_and_visa_card


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vtb.vtb_project.databinding.FragmentShowAuthorizationCardHolderBinding
import com.vtb.vtb_project.ui.open_vtb_card_steps.OpenVtbCardStepsActivity

class ShowAuthorizationCardHolderFragment : Fragment() {
    private  var bindingShow: FragmentShowAuthorizationCardHolderBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingShow = FragmentShowAuthorizationCardHolderBinding.inflate(inflater)
        return bindingShow?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingShow?.btnLogin?.setOnClickListener {
            val intent = Intent(requireContext(), OpenVtbCardStepsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingShow = null
    }
}