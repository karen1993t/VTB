package com.vtb.vtb_project.sign_in

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
import com.vtb.vtb_project.databinding.FragmentRepeatPasswordBinding

class RepeatPasswordFragment : Fragment() {
    var pin2 = ""
    var pin1 = ""
    private val viewModel: ViewModelSignIn by activityViewModels()
    private val viewModelClose: ViewModelSignIn by activityViewModels()

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
           viewModelClose.closeSignIn(true)
        }
        viewModel.pin1LiveData.observe(viewLifecycleOwner, {
            pin1 = it
        })

        bindingRepeatPasswordFragment.pinView1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                pin2 = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                when {
                    bindingRepeatPasswordFragment.pinView1.editableText.toString().length == 5 && pin1 == pin2 -> {
                                Navigation.findNavController(view)
                                    .navigate(R.id.action_repeatPasswordFragment_to_personalAreaFragment)
                    }
                    bindingRepeatPasswordFragment.pinView1.editableText.toString().length == 5 && pin1 != pin2 -> {
                        Toast.makeText(
                            context,
                            getString(R.string.password_not_match),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        )
    }
}