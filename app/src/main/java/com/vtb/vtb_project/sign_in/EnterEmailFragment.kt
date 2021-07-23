package com.vtb.vtb_project.sign_in

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.authorization.Authorization
import com.vtb.vtb_project.create_account_and_visa_card.ShowAuthorizationActivity
import com.vtb.vtb_project.databinding.FragmentEnterEmailBinding

class EnterEmailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_email, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingEnterEmailFragment = FragmentEnterEmailBinding.bind(view)


        bindingEnterEmailFragment.editEmail.editText?.addTextChangedListener(
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
                        bindingEnterEmailFragment.editEmail.editText!!.length() == 0 -> {
                            bindingEnterEmailFragment.editEmail.error = "enter email"
                        }
                        else -> {
                            bindingEnterEmailFragment.editEmail.error = null
                        }

                    }
                }

            }
        )
        bindingEnterEmailFragment.iconClose.setOnClickListener {
            startActivity(Intent(context, ShowAuthorizationActivity::class.java))
            Authorization().finish()
        }
        bindingEnterEmailFragment.buttonLogIn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_enterEmailFragment_to_enterPasswordFragment)
        }
    }


}