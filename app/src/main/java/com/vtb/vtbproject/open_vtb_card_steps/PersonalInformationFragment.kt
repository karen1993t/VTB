package com.vtb.vtbproject.open_vtb_card_steps

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtbproject.R
import com.vtb.vtbproject.databinding.FragmentPersonalInformationBinding
import java.text.SimpleDateFormat
import java.util.*

class PersonalInformationFragment : Fragment() {
    private lateinit var showBinding: FragmentPersonalInformationBinding
    private lateinit var calendar: Calendar
    private lateinit var formatDate: String
    private val shareViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_personal_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showBinding = FragmentPersonalInformationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        showBinding.btnClose.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_goToHomeFragment)
        }

    }


    override fun onResume() {
        super.onResume()
        showBinding.editUserDateOfBirth.setOnClickListener {
            calendar = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()
                }

            showBinding.editUserDateOfBirth.setOnClickListener {
                DatePickerDialog(
                    requireContext(), dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
        // create Country
        val country = resources.getStringArray(R.array.country_name)
        val arrayAdapterCountry =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, country)
        showBinding.autoCompleteUserCountry.setAdapter(arrayAdapterCountry)


        // create citizen ship
        val citizenShip = resources.getStringArray(R.array.citizen_ship)
        val arrayAdapterCitizenShip =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, citizenShip)
        showBinding.editUserCitizenShip.setAdapter(arrayAdapterCitizenShip)

        // create gender
        val gender = resources.getStringArray(R.array.gender)
        val arrayAdapterGender =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, gender)
        showBinding.editUserGender.setAdapter(arrayAdapterGender)


        showBinding.btnGoToMobilePhoneNumberFragment.setOnClickListener {
            shareViewModel.setCountryName(arrayAdapterCountry.getPosition(showBinding.autoCompleteUserCountry.text.toString()))
            Navigation.findNavController(showBinding.root)
                .navigate(R.id.action_go_to_mobile_phone_number)
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(myFormat, Locale.US)
        formatDate = simpleDateFormat.format(calendar.time)
        showBinding.editUserDateOfBirth.setText(formatDate)
    }
}