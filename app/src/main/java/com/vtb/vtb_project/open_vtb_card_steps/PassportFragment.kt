package com.vtb.vtb_project.open_vtb_card_steps


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
import com.vtb.vtb_project.databinding.FragmentPassportBinding
import com.vtb.vtb_project.tools.ToolsForEditText


class PassportFragment : Fragment() {
    private lateinit var showBinding: FragmentPassportBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var passportInputTypSlotsArray: Array<String>
    private lateinit var socialCardTypSlotsArray: Array<String>
    private var indexCitizenShip = 0
    private var minCountSymbol = 0
    private var minCountSymbolSocialCard = 0
    private var isCheckedPassportNumber = false
    private var isCheckedSocialCardNumber = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBinding = FragmentPassportBinding.inflate(inflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passportInputTypSlotsArray = resources.getStringArray(R.array.slots_type_passport_number)
        socialCardTypSlotsArray = resources.getStringArray(R.array.slots_type_social_card)
        showBinding.btnClose.setOnClickListener {
            Navigation.findNavController(showBinding.root)
                .navigate(R.id.action_passportFragment_go_to_home)
        }

    }


    override fun onResume() {
        super.onResume()
        //region create inputType passwordNumber
        sharedViewModel.citizenShipIndexLiveData.observe(viewLifecycleOwner, {
            indexCitizenShip = it
            ToolsForEditText.createMaskEdit(
                it,
                passportInputTypSlotsArray,
                showBinding.editUserPassportNumber
            )
            when (it) {
                0 -> {
                    minCountSymbol = 8
                    minCountSymbolSocialCard = 11
                }
                1 -> {
                    minCountSymbol = 11
                    minCountSymbolSocialCard = 8
                }
            }
        })
        //endregion
        //region checked password number
        showBinding.editUserPassportNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(textPassport: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (textPassport != null) {
                    when {

                        showBinding.editUserPassportNumber.text.isNullOrEmpty() -> {
                            isCheckedPassportNumber = false
                            showBinding.textInputLayoutPassportNumber.isErrorEnabled = true
                            showBinding.textInputLayoutPassportNumber.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        }
                        textPassport.length < minCountSymbol -> {
                            isCheckedPassportNumber = false
                            showBinding.textInputLayoutPassportNumber.isCounterEnabled = true
                            showBinding.textInputLayoutPassportNumber.counterMaxLength =
                                minCountSymbol
                            showBinding.textInputLayoutPassportNumber.error =
                                "${resources.getString(R.string.error_minimum_number)}:$minCountSymbol"
                        }
                        else -> {
                            isCheckedPassportNumber = true
                            showBinding.textInputLayoutPassportNumber.isErrorEnabled = false
                            showBinding.textInputLayoutPassportNumber.error = null
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        //endregion
        //region create dateOfIssue
        showBinding.editUserDateOfIssue.setOnClickListener {
            ToolsForEditText.createDateDialog(
                requireContext(),
                "dd.MM.yyyy",
                showBinding.editUserDateOfIssue
            )
        }
        //endregion
        //region checked dateOfIssue
        showBinding.editUserDateOfIssue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!showBinding.editUserDateOfIssue.text.isNullOrEmpty()) {
                    showBinding.textInputLayoutDateOfIssue.isErrorEnabled = false
                    showBinding.textInputLayoutDateOfIssue.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //endregion

        //region create ExpiryDate
        showBinding.autoCompleteExpiryDate.setOnClickListener {
            ToolsForEditText.createDateDialog(
                requireContext(),
                "dd.MM.yyyy",
                showBinding.autoCompleteExpiryDate
            )
        }
        //endregion
        //region checked expiryDate
        showBinding.autoCompleteExpiryDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!showBinding.autoCompleteExpiryDate.text.isNullOrEmpty()) {
                    showBinding.textInputLayoutExpiryDate.isErrorEnabled = false
                    showBinding.textInputLayoutExpiryDate.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //endregion
        //region  create socialCard
        ToolsForEditText.createMaskEdit(
            indexCitizenShip,
            socialCardTypSlotsArray,
            showBinding.editSocialCardNumber
        )
        //endregion
        //region checked socialCard
        showBinding.editSocialCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(textSocialCard: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (textSocialCard != null) {
                    when {

                        showBinding.editSocialCardNumber.text.isNullOrEmpty() -> {
                            isCheckedSocialCardNumber = false
                            showBinding.textInputLayoutSocialCardNumber.isErrorEnabled = true
                            showBinding.textInputLayoutSocialCardNumber.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        }
                        textSocialCard.length < minCountSymbolSocialCard -> {
                            isCheckedSocialCardNumber = false
                            showBinding.textInputLayoutSocialCardNumber.isCounterEnabled = true
                            showBinding.textInputLayoutSocialCardNumber.counterMaxLength =
                                minCountSymbolSocialCard
                            showBinding.textInputLayoutSocialCardNumber.error =
                                "${resources.getString(R.string.error_minimum_number)}:$minCountSymbolSocialCard"
                        }
                        else -> {
                            isCheckedSocialCardNumber = true
                            showBinding.textInputLayoutSocialCardNumber.isErrorEnabled = false
                            showBinding.textInputLayoutSocialCardNumber.error = null
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        //endregion
        showBinding.btnGoToCommunicationFragment.setOnClickListener {
            when {
                isCheckedPassportNumber &&
                        !showBinding.editUserDateOfIssue.text.isNullOrEmpty() &&
                        !showBinding.autoCompleteExpiryDate.text.isNullOrEmpty() &&
                        isCheckedSocialCardNumber -> {
                    Navigation.findNavController(showBinding.root)
                        .navigate(R.id.action_passportFragment_to_communicationFragment)
                }
                else -> {
                    when {
                        !isCheckedPassportNumber ->
                            showBinding.textInputLayoutPassportNumber.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        showBinding.editUserDateOfIssue.text.isNullOrEmpty() ->
                            showBinding.textInputLayoutDateOfIssue.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        showBinding.autoCompleteExpiryDate.text.isNullOrEmpty() ->
                            showBinding.textInputLayoutExpiryDate.error =
                                resources.getString(R.string.errors_go_to_click_next)
                        !isCheckedSocialCardNumber ->
                            showBinding.textInputLayoutSocialCardNumber.error =
                                resources.getString(R.string.errors_go_to_click_next)
                    }
                }
            }
        }
    }
}