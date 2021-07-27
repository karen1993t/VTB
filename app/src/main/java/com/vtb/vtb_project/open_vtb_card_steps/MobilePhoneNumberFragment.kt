package com.vtb.vtb_project.open_vtb_card_steps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentMobilePhoneNumberBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class MobilePhoneNumberFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var showBinding: FragmentMobilePhoneNumberBinding
    private lateinit var nameSlotsTypeArray: Array<String>
    var minCountSymbol = 0
    var isCheckNumberPhone = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobile_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBinding = FragmentMobilePhoneNumberBinding.bind(view)
        nameSlotsTypeArray = resources.getStringArray(R.array.slots_type)

        showBinding.btnClose.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_showMobilePhoneNumberFragment_to_home)
        }

    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.countryIndexLiveData.observe(viewLifecycleOwner, {
            val slots = PhoneNumberUnderscoreSlotsParser().parseSlots(nameSlotsTypeArray[it])
            val formatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
            formatWatcher.installOn(showBinding.editUserMobileNumber)
            when (it) {
                0 -> minCountSymbol = 15
                1 -> minCountSymbol = 16
            }
        })

        showBinding.editUserMobileNumber.addTextChangedListener(object : TextWatcher {
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
                        showBinding.textInputLayoutMobileNumber.isCounterEnabled = true
                        showBinding.textInputLayoutMobileNumber.counterMaxLength = minCountSymbol
                        showBinding.textInputLayoutMobileNumber.error =
                            "minimum number:$minCountSymbol"

                    } else {
                        showBinding.textInputLayoutMobileNumber.isCounterEnabled = false
                        showBinding.textInputLayoutMobileNumber.error = null
                        isCheckNumberPhone = true
                    }
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })

        showBinding.btnGoToLegalAddressFragment.setOnClickListener {
            if (isCheckNumberPhone) {
                Navigation.findNavController(showBinding.root)
                    .navigate(R.id.action_go_to_legalAddressFragment)
            } else   Toast.makeText(
                requireContext(),
                resources.getString(R.string.errors_go_to_click_next),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}