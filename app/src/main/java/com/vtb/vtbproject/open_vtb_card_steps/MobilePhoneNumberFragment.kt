package com.vtb.vtbproject.open_vtb_card_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtbproject.R
import com.vtb.vtbproject.databinding.FragmentMobilePhoneNumberBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class MobilePhoneNumberFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var showBinding: FragmentMobilePhoneNumberBinding
    private lateinit var nameSlotsTypeArray: Array<String>

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
        })
    }
}