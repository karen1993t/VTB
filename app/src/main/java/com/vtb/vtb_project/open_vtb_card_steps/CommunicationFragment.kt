package com.vtb.vtb_project.open_vtb_card_steps

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentCommunicationBinding

class CommunicationFragment : Fragment() {
    private lateinit var showBinding: FragmentCommunicationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showBinding = FragmentCommunicationBinding.inflate(inflater)
        return showBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redactorTextPrivacyPolicy()

        showBinding.btnClose.setOnClickListener {
            Navigation.findNavController(showBinding.root)
                .navigate(R.id.action_communicationFragment_to_showVtbCardThreeStepsFragment)
        }
    }

    private fun redactorTextPrivacyPolicy() {
        val spannable = SpannableStringBuilder(showBinding.textPrivacyPolicy.text).apply {
            setSpan(
                ForegroundColorSpan(resources.getColor(R.color.color_red_card_steps, null)),
                11,
                26,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(resources.getColor(R.color.color_red_card_steps, null)),
                29,
                length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        showBinding.textPrivacyPolicy.text = spannable

    }
}