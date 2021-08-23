package com.vtb.vtb_project.ui.open_vtb_card_steps


import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentCommunicationBinding

class CommunicationFragment : Fragment() {
    var showBinding: FragmentCommunicationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showBinding = FragmentCommunicationBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redactorTextPrivacyPolicy()

        val communicationStatementArray =
            resources.getStringArray(R.array.communication_type_statement)
        val communicationWithTheBankArray =
            resources.getStringArray(R.array.communication_type_obtaining_with_the_bank)
        val arrayAdapterStatement = ArrayAdapter(
            requireContext(), R.layout.drop_down_item_country,
            communicationStatementArray
        )
        showBinding?.editObtainingTheStatement?.setAdapter(arrayAdapterStatement)

        val arrayAdapterWithTheBank = ArrayAdapter(
            requireContext(), R.layout.drop_down_item_country,
            communicationWithTheBankArray
        )
        showBinding?.editCommunicationWithTheBank?.setAdapter(arrayAdapterWithTheBank)

        showBinding?.btnClose?.setOnClickListener {
            showBinding?.root?.let { view ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_communicationFragment_to_showVtbCardThreeStepsFragment)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        showBinding?.editObtainingTheStatement?.setOnClickListener {
            showBinding?.editObtainingTheStatement?.showDropDown()
        }

        showBinding?.editCommunicationWithTheBank?.setOnClickListener {
            showBinding?.editCommunicationWithTheBank?.showDropDown()
        }
        showBinding?.editObtainingTheStatement?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!showBinding?.editObtainingTheStatement?.text.isNullOrEmpty()) {
                    showBinding?.textInputLayoutObtainingTheStatement?.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        showBinding?.editCommunicationWithTheBank?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!showBinding?.editCommunicationWithTheBank?.text.isNullOrEmpty()) {
                    showBinding?.textInputLayoutCommunicationWithTheBank?.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        showBinding?.btnGoToCheckedPersonalInformation?.setOnClickListener {
            when {
                showBinding?.checkBoxPrivacyPolicy?.isChecked == true &&
                        !showBinding?.editObtainingTheStatement?.text.isNullOrEmpty() &&
                        !showBinding?.editCommunicationWithTheBank?.text.isNullOrEmpty() -> {
                    showBinding?.root?.let { view ->
                        Navigation.findNavController(view)
                            .navigate(R.id.action_go_to_bankAuthorizationSuccessFragment)
                    }
                }
                else -> {
                    when {
                        showBinding?.editObtainingTheStatement?.text.isNullOrEmpty() ->
                            showBinding?.textInputLayoutObtainingTheStatement?.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        showBinding?.editCommunicationWithTheBank?.text.isNullOrEmpty() ->
                            showBinding?.textInputLayoutCommunicationWithTheBank?.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        showBinding?.checkBoxPrivacyPolicy?.isChecked == false ->
                            Toast.makeText(
                                requireContext(),
                                "Pleas check Privacy Policy",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                }
            }
        }
    }


    private fun redactorTextPrivacyPolicy() {
        val spannable = SpannableStringBuilder(showBinding?.textPrivacyPolicy?.text).apply {
            setSpan(
                ForegroundColorSpan(resources.getColor(R.color.color_red_card_steps, null)),
                11,
                26,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(resources.getColor(R.color.color_red_card_steps, null)),
                29,
                length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        showBinding?.textPrivacyPolicy?.text = spannable

    }

    override fun onDestroy() {
        super.onDestroy()
        showBinding = null
    }
}