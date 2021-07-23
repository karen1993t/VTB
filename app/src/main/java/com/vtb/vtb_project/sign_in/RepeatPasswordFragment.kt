package com.vtb.vtb_project.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentRepeatPasswordBinding

class RepeatPasswordFragment : Fragment() {

    var pin2 = ""
    var pin1 = ""
    private val viewModel: ViewModelSignIn by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repeat_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingRepeatPasswordFragment = FragmentRepeatPasswordBinding.bind(view)
        bindingRepeatPasswordFragment.iconClose.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_enterPasswordFragment_to_enterEmailFragment)
        }
        viewModel.pin1LiveData.observe(viewLifecycleOwner, {
            pin1 = it
        })


        val requestFocus = bindingRepeatPasswordFragment.editTextRep.requestFocus()
        bindingRepeatPasswordFragment.editTextRep.inputType = InputType.TYPE_CLASS_NUMBER
        bindingRepeatPasswordFragment.editTextRep.isFocusableInTouchMode
        bindingRepeatPasswordFragment.editTextRep.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {


                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    pin2 = s.toString()


                }

                override fun afterTextChanged(s: Editable?) {
                    when (bindingRepeatPasswordFragment.editTextRep.editableText.toString().length) {
                        1 -> {
                            bindingRepeatPasswordFragment.imageviewRepCircle1.setImageResource(R.drawable.circle2)
                        }
                        2 -> {
                            bindingRepeatPasswordFragment.imageviewRepCircle2.setImageResource(R.drawable.circle2)
                        }
                        3 -> {
                            bindingRepeatPasswordFragment.imageviewRepCircle3.setImageResource(R.drawable.circle2)
                        }
                        4 -> {
                            bindingRepeatPasswordFragment.imageviewRepCircle4.setImageResource(R.drawable.circle2)
                        }
                        5 -> {
                            bindingRepeatPasswordFragment.imageviewRepCircle5.setImageResource(R.drawable.circle2)
                        }
                    }
                    if (bindingRepeatPasswordFragment.editTextRep.editableText.toString().length == 5 && pin1 == pin2) {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_repeatPasswordFragment_to_useFaceIDFragment)
                    } else if (bindingRepeatPasswordFragment.editTextRep.editableText.toString().length == 5 && pin1 != pin2) {
                        Toast.makeText(
                            context,
                            getString(R.string.password_not_match),
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }

            }
        )


    }


}