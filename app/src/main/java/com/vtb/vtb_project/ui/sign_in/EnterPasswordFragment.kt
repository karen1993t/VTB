package com.vtb.vtb_project.ui.sign_in

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
import com.vtb.vtb_project.databinding.FragmentEnterPasswordBinding
import com.vtb.vtb_project.view_model.ViewModelSignIn

class EnterPasswordFragment : Fragment() {
    private lateinit var pin1: String
    val viewModel: ViewModelSignIn by activityViewModels()
    private var bindingEnterPasswordFragment : FragmentEnterPasswordBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingEnterPasswordFragment= FragmentEnterPasswordBinding.inflate(inflater)
        return bindingEnterPasswordFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingEnterPasswordFragment?.iconClose?.setOnClickListener {
            viewModel.closeSignIn(true)
        }

        bindingEnterPasswordFragment?.pinView?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                pin1 = s.toString()

                if (bindingEnterPasswordFragment?.pinView?.editableText.toString().length == 5) {
                    viewModel.setPin1(pin1)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_enterPasswordFragment_to_repeatPasswordFragment)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingEnterPasswordFragment=null
    }
}


