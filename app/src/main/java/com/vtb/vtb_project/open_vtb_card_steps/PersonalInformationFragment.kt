package com.vtb.vtb_project.open_vtb_card_steps


import android.app.DatePickerDialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentPersonalInformationBinding
import java.text.SimpleDateFormat
import java.util.*

class PersonalInformationFragment : Fragment() {
    private lateinit var showBinding: FragmentPersonalInformationBinding
    private lateinit var calendar: Calendar
    private lateinit var formatDate: String
    private lateinit var arrayAdapterCountry: ArrayAdapter<String>
    private lateinit var arrayAdapterCitizenShip: ArrayAdapter<String>
    private lateinit var arrayAdapterGender: ArrayAdapter<String>
    private var isCheckedCountry: Boolean = false
    private var isCheckedCitizenship: Boolean = false
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
            createDateDialog()
        }
        // create Country
        val country = resources.getStringArray(R.array.country_name)
        arrayAdapterCountry =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, country)
        showBinding.autoCompleteUserCountry.setAdapter(arrayAdapterCountry)

        //checked Country Edit
        showBinding.autoCompleteUserCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                autoCompleteUserCountry:
                CharSequence?, p1: Int, p2: Int, p3: Int
            ) {
                if (!country.contains(autoCompleteUserCountry.toString())) {
                    showBinding.textInputLayoutCountry.isErrorEnabled = true
                    showBinding.textInputLayoutCountry.error =
                        resources.getString(R.string.error_editText)
                    isCheckedCountry = false
                } else {
                    showBinding.textInputLayoutCountry.isErrorEnabled = false
                    showBinding.textInputLayoutCountry.error = null
                    isCheckedCountry = true
                }
            }

            override fun afterTextChanged(autoCompleteUserCountry: Editable?) {
            }
        })

        // create citizen ship
        val citizenShip = resources.getStringArray(R.array.citizen_ship)
        arrayAdapterCitizenShip =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, citizenShip)
        showBinding.editUserCitizenShip.setAdapter(arrayAdapterCitizenShip)

        //checked CitizenShip Edit
        showBinding.editUserCitizenShip.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                editUserCitizenShip:
                CharSequence?, p1: Int, p2: Int, p3: Int
            ) {
                if (!citizenShip.contains(editUserCitizenShip.toString())) {
                    showBinding.textInputLayoutCitizenShip.isErrorEnabled = true
                    showBinding.textInputLayoutCitizenShip.error =
                        resources.getString(R.string.error_editText)
                    isCheckedCitizenship = false
                } else {
                    showBinding.textInputLayoutCitizenShip.isErrorEnabled = false
                    showBinding.textInputLayoutCitizenShip.error = null
                    isCheckedCitizenship = true
                }
            }

            override fun afterTextChanged(autoCompleteUserCountry: Editable?) {
            }
        })

        // create gender
        val gender = resources.getStringArray(R.array.gender)
        arrayAdapterGender =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, gender)
        showBinding.editUserGender.setAdapter(arrayAdapterGender)


        showBinding.btnGoToMobilePhoneNumberFragment.setOnClickListener {
            sendData()
            when {
                isCheckedCitizenship && isCheckedCountry &&
                        !showBinding.editUserFirstName.text.isNullOrEmpty() &&
                        !showBinding.editUserSurname.text.isNullOrEmpty() &&
                        !showBinding.editUserDateOfBirth.text.isNullOrEmpty() &&
                        !showBinding.editUserGender.text.isNullOrEmpty() -> {

                    Navigation.findNavController(showBinding.root)
                        .navigate(R.id.action_go_to_mobile_phone_number)
                }
                else -> Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.error_go_to_mobileFragment),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(myFormat, Locale.US)
        formatDate = simpleDateFormat.format(calendar.time)
        showBinding.editUserDateOfBirth.setText(formatDate)
    }

    private fun createDateDialog() {
        calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        val dialog = DatePickerDialog(
            requireContext(), dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    private fun sendData() {
        shareViewModel.setCountryName(
            arrayAdapterCountry.getPosition(
                showBinding.autoCompleteUserCountry.text.toString()
            )
        )
        shareViewModel.setCitizenShipName(
            arrayAdapterCitizenShip.getPosition(
                showBinding.editUserCitizenShip.text.toString()
            )
        )
        shareViewModel.setGenderName(showBinding.editUserGender.text.toString())
        shareViewModel.inputUserFirstName(showBinding.editUserFirstName.text.toString())
        shareViewModel.inputUserSurName(showBinding.editUserSurname.text.toString())
        shareViewModel.inputDateBirthName(showBinding.editUserDateOfBirth.text.toString())
    }
}