package com.vtb.vtb_project.authorization

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.create_account_and_visa_card.ShowAuthorizationActivity
import com.vtb.vtb_project.databinding.FragmentAuthorizationBinding


class AuthorizationFirstFragment : Fragment() {
    private lateinit var password1: String
    private lateinit var password2: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_authorization_, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingAuthFirst = FragmentAuthorizationBinding.bind(view)



        bindingAuthFirst.buttonLogIn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_authorizationFirstFragment_to_authorizationFragmentSendEmail)
        }
        bindingAuthFirst.iconClose.setOnClickListener {
            startActivity(Intent(context, ShowAuthorizationActivity::class.java))
            Authorization().finish()
        }


        bindingAuthFirst.editPass.editText?.addTextChangedListener(
            object : TextWatcher {

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    password1 = p0.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    when {
                        bindingAuthFirst.editPass.editText!!.length() == 0 -> {
                            bindingAuthFirst.editPass.error = getString(R.string.enter_password)
                        }
                        bindingAuthFirst.editPass.editText!!.length() < 6 -> {

                            bindingAuthFirst.editPass.error = getString(R.string.min_length)
                        }
                        else -> {
                            bindingAuthFirst.editPass.error = null
                        }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            }
        )

        bindingAuthFirst.repPass.editText?.addTextChangedListener(
            object : TextWatcher {

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    password2 = p0.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    when {
                        bindingAuthFirst.repPass.editText!!.length() == 0 -> {
                            bindingAuthFirst.repPass.error = getString(R.string.repeat_password)
                        }
                        bindingAuthFirst.repPass.editText!!.length() < 6 -> {

                            bindingAuthFirst.repPass.error = getString(R.string.min_length)
                        }
                        password2 != password1 -> {
                            bindingAuthFirst.repPass.error = getString(R.string.password_not_match)
                        }
                        else -> {
                            bindingAuthFirst.repPass.error = null
                        }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            }
        )
        bindingAuthFirst.editEmail.editText?.addTextChangedListener(
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
//                        !bindingAuthFirst.editEmail.editText.toString().endsWith("@gmail.com") -> {
//                            bindingAuthFirst.editEmail.error = "enter @gmail.com"
//                        }
                        bindingAuthFirst.editEmail.editText!!.length() == 0 -> {
                            bindingAuthFirst.editEmail.error = getString(R.string.enter_email)
                        }
//                        bindingAuthFirst.editEmail.editText!!.length() <= 12->{
//                            bindingAuthFirst.editEmail.error =  "enter correct email"
//                        }
                        else -> {
                            bindingAuthFirst.editEmail.error = null
                        }

                    }
                }

            }
        )
    }
}


