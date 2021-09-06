package com.vtb.vtb_project.ui.authorization

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
import com.vtb.vtb_project.databinding.FragmentAuthorizationBinding
import com.vtb.vtb_project.view_model.ViewModelAuthorization

class AuthorizationFirstFragment : Fragment() {

    private var bindingAuthFirst: FragmentAuthorizationBinding? = null
    private lateinit var password1: String
    private lateinit var password2: String
    private val viewModelClos: ViewModelAuthorization by activityViewModels()
    private var checkerEnterPassword: Boolean = false
    private var checkerRepeatPassword: Boolean = false
    private var checkerEnterEmail: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         bindingAuthFirst = FragmentAuthorizationBinding.inflate(inflater)
        return bindingAuthFirst?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindingAuthFirst?.buttonLogIn?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_authorizationFirstFragment_to_authorizationFragmentSendEmail)
        }

        bindingAuthFirst?.iconClose?.setOnClickListener {
            viewModelClos.closeAuthorization(true)
        }

        bindingAuthFirst?.editPass?.editText?.addTextChangedListener(
            object : TextWatcher {

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    password1 = p0.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    when {
                        bindingAuthFirst?.editPass?.editText?.text.isNullOrEmpty() -> {
                            bindingAuthFirst?.editPass?.error = getString(R.string.enter_password)
                        }
                        bindingAuthFirst?.editPass?.editText?.text?.length!! < 6 -> {
                            bindingAuthFirst?.editPass?.error = getString(R.string.min_length)
                        }


                        else -> {
                            bindingAuthFirst?.editPass?.error = null
                            checkerEnterPassword = true
                        }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            }
        )

        bindingAuthFirst?.repPass?.editText?.addTextChangedListener(
            object : TextWatcher {

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    password2 = p0.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    when {
                        bindingAuthFirst?.repPass?.editText?.text?.isEmpty() == true -> {
                            bindingAuthFirst?.repPass?.error = getString(R.string.repeat_password)
                        }
                        bindingAuthFirst?.repPass?.editText?.text?.length!!< 6 -> {

                            bindingAuthFirst?.repPass?.error = getString(R.string.min_length)
                        }
                        password2 != password1 -> {
                            bindingAuthFirst?.repPass?.error = getString(R.string.password_not_match)
                        }
                        else -> {
                            bindingAuthFirst?.repPass?.error = null
                            checkerRepeatPassword = true
                        }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            }
        )
        bindingAuthFirst?.editEmail?.editText?.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    when {
                        !bindingAuthFirst?.editEmail?.editText?.text.toString()
                            .endsWith(getString(R.string.gmail_com)) -> {
                            bindingAuthFirst?.editEmail?.error = getString(R.string.enter_gmail_com)
                        }
                        bindingAuthFirst?.editEmail?.editText?.text?.isEmpty() == true -> {
                            bindingAuthFirst?.editEmail?.error = getString(R.string.enter_email)
                        }
                        bindingAuthFirst?.editEmail?.editText?.text?.length!! <= 12 -> {
                            bindingAuthFirst?.editEmail?.error =
                                getString(R.string.enter_correct_email)
                        }
                        else -> {
                            bindingAuthFirst?.editEmail?.error = null
                            checkerEnterEmail = true
                        }

                    }
                }

            }
        )
        bindingAuthFirst?.buttonLogIn?.setOnClickListener {
            when {
                checkerEnterPassword && checkerRepeatPassword && checkerEnterEmail -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_authorizationFirstFragment_to_authorizationFragmentSendEmail)
                }
                !checkerEnterEmail -> {
                    bindingAuthFirst?.editEmail?.error = getString(R.string.enter_email)
                }
                !checkerEnterPassword -> {
                    bindingAuthFirst?.editPass?.error = getString(R.string.enter_password)
                }
                !checkerRepeatPassword -> {
                    bindingAuthFirst?.repPass?.error = getString(R.string.repeat_password)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingAuthFirst= null
    }
}
