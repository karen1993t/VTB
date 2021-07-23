package com.vtb.vtb_project.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentEnterPasswordBinding

class EnterPasswordFragment : Fragment() {
    private lateinit var pin1: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingEnterPasswordFragment = FragmentEnterPasswordBinding.bind(view)
        bindingEnterPasswordFragment.iconClose.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_enterPasswordFragment_to_enterEmailFragment)
        }
        val requestFocus = bindingEnterPasswordFragment.editTextEnter.requestFocus()
        bindingEnterPasswordFragment.editTextEnter.inputType = TYPE_CLASS_NUMBER
        bindingEnterPasswordFragment.editTextEnter.isFocusableInTouchMode
        bindingEnterPasswordFragment.editTextEnter.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (bindingEnterPasswordFragment.editTextEnter.editableText.toString().length == 5) {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_enterPasswordFragment_to_repeatPasswordFragment)
                    }
                    pin1 = s.toString()

                }

                override fun afterTextChanged(s: Editable?) {
                    when (bindingEnterPasswordFragment.editTextEnter.editableText.toString().length) {
                        1 -> {
                            bindingEnterPasswordFragment.imageviewCircle1.setImageResource(R.drawable.circle2)
                        }
                        2 -> {
                            bindingEnterPasswordFragment.imageviewCircle2.setImageResource(R.drawable.circle2)
                        }
                        3 -> {
                            bindingEnterPasswordFragment.imageviewCircle3.setImageResource(R.drawable.circle2)
                        }
                        4 -> {
                            bindingEnterPasswordFragment.imageviewCircle4.setImageResource(R.drawable.circle2)
                        }
                        5 -> {
                            bindingEnterPasswordFragment.imageviewCircle5.setImageResource(R.drawable.circle2)
                        }
                    }

                }

            }
        )
//        val intentEnterPassword = Intent(context, RepeatPasswordFragment::class.java)
//        intentEnterPassword.putExtra("pin1", pin1)

    }


}


