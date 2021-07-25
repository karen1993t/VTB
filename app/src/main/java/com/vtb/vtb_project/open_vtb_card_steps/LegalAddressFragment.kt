package com.vtb.vtb_project.open_vtb_card_steps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentLegalAddressBinding

class LegalAddressFragment : Fragment() {
    private val shareViewModel: SharedViewModel by activityViewModels()
    private var indexArrayOfCountry = 0
    private var isCheckedCountry: Boolean = false
    private var isCheckedCity: Boolean = false
    private lateinit var showBinding: FragmentLegalAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBinding = FragmentLegalAddressBinding.inflate(inflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareViewModel.countryIndexLiveData.observe(viewLifecycleOwner, {
            indexArrayOfCountry = it
        })

        showBinding.btnClose.setOnClickListener {
            Navigation.findNavController(showBinding.root)
                .navigate(R.id.action_legalAddressFragment_go_to_home)
        }
    }

    override fun onResume() {
        super.onResume()

        // create Country
        val country = resources.getStringArray(R.array.country_name)
        val arrayAdapterCountry =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, country)
        showBinding.autoCompleteUserCountry.setAdapter(arrayAdapterCountry)
        showBinding.autoCompleteUserCountry.setText(country[indexArrayOfCountry])

        // checked country editText

        showBinding.autoCompleteUserCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!country.contains(showBinding.autoCompleteUserCountry.text.toString()) ||
                    showBinding.autoCompleteUserCountry.text.toString() != country.elementAt(
                        indexArrayOfCountry )) {
                    isCheckedCountry = false
                    showBinding.textInputLayoutCountry.isErrorEnabled = true
                    showBinding.textInputLayoutCountry.error =
                        "${resources.getString(R.string.error_editText)} or not equals Country"

                } else {
                    isCheckedCountry = true
                    showBinding.textInputLayoutCountry.isErrorEnabled = false
                    showBinding.textInputLayoutCountry.error = null

                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // create City
        val allCityList =
            arrayOf(R.array.city_of_armenia, R.array.city_of_russia)  // city of Armenia and Russia
        val city = resources.getStringArray(allCityList[indexArrayOfCountry])
        val arrayAdapterCity =
            ArrayAdapter(requireContext(), R.layout.drop_down_item_country, city)
        showBinding.autoCompleteUserCity.setAdapter(arrayAdapterCity)

        // checked city editText
        showBinding.autoCompleteUserCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!city.contains(showBinding.autoCompleteUserCity.text.toString())) {
                    isCheckedCity = false
                    showBinding.textInputLayoutCity.isErrorEnabled = true
                    showBinding.textInputLayoutCity.error =
                        resources.getString(R.string.error_editText)

                } else {
                    isCheckedCity = true
                    showBinding.textInputLayoutCity.isErrorEnabled = false
                    showBinding.textInputLayoutCity.error = null

                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        showBinding.btnGoToIdentityVerificationFragment.setOnClickListener {
           Log.d("sss","city:$isCheckedCity, country$isCheckedCountry" +
                   "street:${!showBinding.autoCompleteUserStreet.text.isNullOrEmpty()}" +
                   "hous:${!showBinding.editUserHouseNumber.text.isNullOrEmpty()}" +
                   "apart:${ !showBinding.editUserApartment.text.isNullOrEmpty()}"

             )
            when {
                isCheckedCity && isCheckedCountry &&
                        !showBinding.autoCompleteUserStreet.text.isNullOrEmpty() &&
                        !showBinding.editUserHouseNumber.text.isNullOrEmpty() &&
                        !showBinding.editUserApartment.text.isNullOrEmpty() -> {
                    Navigation.findNavController(showBinding.root)
                        .navigate(R.id.action_go_to_passportFragment)
                }
                else ->
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.error_go_to_mobileFragment),
                        Toast.LENGTH_SHORT
                    ).show()

            }

        }
    }
}