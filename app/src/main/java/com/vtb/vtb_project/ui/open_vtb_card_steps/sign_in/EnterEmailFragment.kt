package com.vtb.vtb_project.ui.open_vtb_card_steps.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentEnterEmailBinding
import com.vtb.vtb_project.view_model.ViewModelSignIn

class EnterEmailFragment : Fragment() {
   private var bindingEnterEmailFragment: FragmentEnterEmailBinding?= null
    private val closeBtn: ViewModelSignIn by activityViewModels()
    private  var checkerEmail:Boolean = false
    private lateinit var email:String
    val viewModelEmail :ViewModelSignIn by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingEnterEmailFragment = FragmentEnterEmailBinding.inflate(inflater)
        return bindingEnterEmailFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindingEnterEmailFragment?.iconClose?.setOnClickListener {
            closeBtn.closeSignIn(true)
        }

        bindingEnterEmailFragment?.editEmail?.editText?.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    email= s.toString()
                    viewModelEmail.emailLiveData(email)
                    Log.d("log","send email $email")

                }

                override fun afterTextChanged(s: Editable?) {
                    when {
                        bindingEnterEmailFragment?.editEmail?.editText?.text?.isEmpty() == true -> {
                            bindingEnterEmailFragment?.editEmail?.error =
                                getString(R.string.enter_email)
                        }
                        !bindingEnterEmailFragment?.editEmail?.editText?.text.toString()
                            .endsWith(getString(R.string.gmail_com)) -> {
                            bindingEnterEmailFragment?.editEmail?.error =
                                getString(R.string.enter_gmail_com)
                        }
                        bindingEnterEmailFragment?.editEmail?.editText?.text?.length!! <= 12 -> {
                            bindingEnterEmailFragment?.editEmail?.error =
                                getString(R.string.enter_correct_email)

                        }
                        else -> {
                            bindingEnterEmailFragment?.editEmail?.error = null
                            checkerEmail = true
                        }
                    }
                }
            }
        )

        bindingEnterEmailFragment?.buttonLogIn?.setOnClickListener {
            when {
                bindingEnterEmailFragment?.editEmail?.editText?.text?.isEmpty() == true -> {
                    bindingEnterEmailFragment?.editEmail?.error =
                        getString(R.string.enter_email)
                }
                checkerEmail -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_enterEmailFragment_to_enterPasswordFragment)
                }
            }
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        bindingEnterEmailFragment=null
    }
}