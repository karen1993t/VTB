package com.vtb.vtb_project.ui.open_vtb_card_steps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentMobilePhoneNumberBinding
import com.vtb.vtb_project.tools.ToolsForEditText
import com.vtb.vtb_project.view_model.SharedCardStepsViewModel


class MobilePhoneNumberFragment : Fragment() {
    private val sharedCardStepsViewModel: SharedCardStepsViewModel by activityViewModels()
    var showBinding: FragmentMobilePhoneNumberBinding? = null
    private lateinit var nameSlotsTypeArray: Array<String>
    var minCountSymbol = 0
    var isCheckNumberPhone = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBinding = FragmentMobilePhoneNumberBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameSlotsTypeArray = resources.getStringArray(R.array.slots_type_phone_number)
        showBinding?.btnClose?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_showMobilePhoneNumberFragment_to_home)
        }
    }

    override fun onResume() {
        super.onResume()
        sharedCardStepsViewModel.countryIndexLiveData.observe(viewLifecycleOwner, {
            showBinding?.editUserMobileNumber?.let { inputEdit ->
                ToolsForEditText.createMaskEdit(
                    it,
                    nameSlotsTypeArray,
                    inputEdit
                )
            }
            when (it) {
                0 -> minCountSymbol = 15
                1 -> minCountSymbol = 16
            }
        })
        //region checked phone number
        showBinding?.editUserMobileNumber?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                textUserMobileNumber: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                if (textUserMobileNumber != null)
                    if (textUserMobileNumber.length < minCountSymbol || textUserMobileNumber.isEmpty()) {
                        isCheckNumberPhone = false
                        showBinding?.textInputLayoutMobileNumber?.isCounterEnabled = true
                        showBinding?.textInputLayoutMobileNumber?.counterMaxLength = minCountSymbol
                        showBinding?.textInputLayoutMobileNumber?.error =
                            "${resources.getString(R.string.error_minimum_number)}:$minCountSymbol"

                    } else {
                        showBinding?.textInputLayoutMobileNumber?.isCounterEnabled = false
                        showBinding?.textInputLayoutMobileNumber?.error = null
                        isCheckNumberPhone = true
                    }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        //endregion

        showBinding?.btnGoToLegalAddressFragment?.setOnClickListener {
            if (isCheckNumberPhone) {
                showBinding?.root?.let { view ->
                    sendData()
                    Navigation.findNavController(view)
                        .navigate(R.id.action_go_to_legalAddressFragment)
                }
            } else {
                if (showBinding?.editUserMobileNumber?.text.isNullOrEmpty()) {
                    showBinding?.textInputLayoutMobileNumber?.error =
                        resources.getString(R.string.errors_go_to_click_next)
                }
            }
        }
    }

    private fun sendData() {     //mask address exp:  +374(__)___-___
    sharedCardStepsViewModel.setMobileNumber(showBinding?.editUserMobileNumber?.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        showBinding = null
    }
}