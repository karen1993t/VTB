package com.vtb.vtb_project.ui.personal_area

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
import com.vtb.vtb_project.databinding.FragmentAddCardBinding
import com.vtb.vtb_project.tools.ToolsForEditText
import com.vtb.vtb_project.view_model.ViewModelPersonalArea

class AddCardFragment : Fragment() {
    private var bindingAddCardFragment: FragmentAddCardBinding?= null
    private var checkerNumberCard: Boolean = false
    private var checkerCvcCard: Boolean = false
    private var checkerDateCard: Boolean = false
    private val liveDataBtnDone: ViewModelPersonalArea by activityViewModels()
    private val liveDataGetNumberCard: ViewModelPersonalArea by activityViewModels()
    private val liveDataGetNameCard: ViewModelPersonalArea by activityViewModels()
    private val liveDataSaveCard: ViewModelPersonalArea by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingAddCardFragment = FragmentAddCardBinding.inflate(inflater)
        return bindingAddCardFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindingAddCardFragment?.editNumberCardContainer?.editText?.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                }

                override fun afterTextChanged(p0: Editable?) {

                    liveDataGetNumberCard.setNumberCard(bindingAddCardFragment?.editNumberCard?.text.toString())
                   if(bindingAddCardFragment?.editNumberCard?.text?.startsWith('5') == true){
                      liveDataGetNameCard.setNameCard("MasterCard").toString()
//                        '3'->liveDataGetNameCard.setNameCard("American Express").toString()
//                        '4'->liveDataGetNameCard.setNameCard("Visa Card").toString()
//                        else->{
//                            bindingAddCardFragment?.editNumberCardContainer?.error = getString(R.string.enter_correct_number_card)
//                        }
                    }

                    when {
                        bindingAddCardFragment?.editNumberCardContainer?.editText?.text.isNullOrEmpty() -> {
                            bindingAddCardFragment?.editNumberCardContainer?.error =
                                getString(R.string.enter_number_card)
                        }
                        bindingAddCardFragment?.editNumberCard?.text?.length!! < 19 -> {
                            bindingAddCardFragment?.editNumberCardContainer?.error =
                                getString(R.string.enter_correct_number_card)
                        }
                        else -> {
                            bindingAddCardFragment?.editNumberCardContainer?.error = null
                            checkerNumberCard = true
                        }
                    }
                }

            })



        bindingAddCardFragment?.editCvcCard?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                when {
                    bindingAddCardFragment?.editCvcCard?.text.isNullOrEmpty() -> {
                        bindingAddCardFragment?.editCvcCardContainer?.error =
                            getString(R.string.enter_cvc_card)
                    }
                    bindingAddCardFragment?.editCvcCard?.text?.length!! < 3 -> {
                        bindingAddCardFragment?.editCvcCardContainer?.error =
                            getString(R.string.enter_correct_cvc_card)
                    }
                    else -> {
                        bindingAddCardFragment?.editCvcCardContainer?.error = null
                        checkerCvcCard = true
                    }
                }
            }
        })



        bindingAddCardFragment?.editDateCard?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun afterTextChanged(p0: Editable?) {
                when {
                    bindingAddCardFragment?.editDateCard?.text.isNullOrEmpty() -> {
                        bindingAddCardFragment?.editCvcCardContainer?.error = "enter date card"
                    }

                    else -> {
                        bindingAddCardFragment?.editDateCardContainer?.error = null
                        checkerDateCard = true
                    }

                }

            }

        })

        bindingAddCardFragment?.buttonDone?.setOnClickListener {

           if (bindingAddCardFragment?.switchBtn?.isChecked == true){
               liveDataSaveCard.switchChecker(true)
               Toast.makeText(requireContext(), "Card added successfully", Toast.LENGTH_SHORT)
                   .show()
           }

            when {
                (checkerNumberCard && checkerCvcCard && checkerDateCard ) -> {
                    liveDataBtnDone.selectDoneBtn(true)

                    Navigation.findNavController(view)
                        .navigate(R.id.action_addCardFragment_to_balanceUpFragment)

                }
                !checkerNumberCard -> bindingAddCardFragment?.editNumberCardContainer?.error =
                    getString(R.string.enter_number_card)
                !checkerDateCard -> bindingAddCardFragment?.editDateCardContainer?.error =
                    "enter date card"
                !checkerCvcCard -> bindingAddCardFragment?.editCvcCardContainer?.error =
                    getString(R.string.enter_cvc_card)

            }
        }


    }

    override fun onResume() {
        super.onResume()
        val arrayMasks = arrayOf("____ ____ ____ ____", "__/__")
        ToolsForEditText.createMaskEdit(0, arrayMasks, bindingAddCardFragment?.editNumberCard!!)
        ToolsForEditText.createMaskEdit(1, arrayMasks, bindingAddCardFragment?.editDateCard!!)


//        val slots = PhoneNumberUnderscoreSlotsParser().parseSlots("____ ____ ____ ____")
//        val formatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
//        formatWatcher.installOn(bindingAddCardFragment.editNumberCard)


    }

    override fun onDestroy() {
        super.onDestroy()
        bindingAddCardFragment= null
    }
}