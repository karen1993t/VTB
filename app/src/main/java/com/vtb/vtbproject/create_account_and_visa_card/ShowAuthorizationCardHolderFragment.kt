package com.vtb.vtbproject.create_account_and_visa_card

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.vtb.vtbproject.R
import com.vtb.vtbproject.databinding.FragmentShowAuthorizationCardHolderBinding
import com.vtb.vtbproject.open_vtb_card_steps.OpenVtbCardStepsActivity

class ShowAuthorizationCardHolderFragment : Fragment() {
    private lateinit var bindingShow: FragmentShowAuthorizationCardHolderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_authorization_card_holder, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingShow = FragmentShowAuthorizationCardHolderBinding.bind(view)
        bindingShow.btnLogin.setOnClickListener {
            val intent = Intent(context, OpenVtbCardStepsActivity::class.java)
            startActivity(intent)
        }
        bindingShow.visaCard.setOnClickListener {
            changeToMarginTopConstraint(bindingShow.containerLayoutUser)
            bindingShow.component.background =
                resources.getDrawable(R.drawable.background_component_gray, null)
        }
    }

    private fun changeToMarginTopConstraint(constrainLayout: ConstraintLayout) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(constrainLayout)
        constraintSet.setMargin(
            R.id.rectangle_1,
            ConstraintSet.TOP,
            resources.getDimensionPixelSize(R.dimen.top_margin)
        )
        constraintSet.applyTo(constrainLayout)

    }
}