package com.vtb.vtb_project.ui.personal_area

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.PersonalAreaAdapter
import com.vtb.vtb_project.databinding.FragmentPersonalAreaBinding
import com.vtb.vtb_project.view_model.ViewModelPersonalArea
import java.util.concurrent.Executor

class PersonalAreaFragment : Fragment() {
    private var bindingPersonalAreaFragment: FragmentPersonalAreaBinding?= null
    private val fragmentStatusViewModel:ViewModelPersonalArea by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val executor = ContextCompat.getMainExecutor(requireContext())
        val biometricManager = BiometricManager.from(requireContext())

        fun authUser(executor: Executor) {

            val promptInfo = BiometricPrompt.PromptInfo.Builder()

                .setTitle("Authentication Required!")
                .setSubtitle("Important authentication")
                .setDescription("Please authenticate to be able to view your account information ")
                .setDeviceCredentialAllowed(true)
                .build()

            val biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
//                        super.onAuthenticationSucceeded(result)
//                        fragment_fingerprint.visibility = View.VISIBLE
                    }


                    override fun onAuthenticationError(
                        errorCode: Int, errString: CharSequence
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(
                            requireContext(),
                            "error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })


            biometricPrompt.authenticate(promptInfo)

        }

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                authUser(executor)
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(
                    requireContext(),
                    "BIOMETRIC_ERROR_NO_HARDWARE",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(
                    requireContext(),
                    "BIOMETRIC_ERROR_HW_UNAVAILABLE",
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(
                    requireContext(),
                    "BIOMETRIC_ERROR_NONE_ENROLLED",
                    Toast.LENGTH_LONG
                ).show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        bindingPersonalAreaFragment = FragmentPersonalAreaBinding.inflate(inflater)
        return bindingPersonalAreaFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val spannable = SpannableStringBuilder(bindingPersonalAreaFragment?.titleMoney?.text)
        spannable.setSpan(
            ForegroundColorSpan(Color.GRAY), 6, 13,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        bindingPersonalAreaFragment?.titleMoney?.text = spannable

        bindingPersonalAreaFragment?.pay?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_scan_destination)
        }
        bindingPersonalAreaFragment?.replenish?.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_personalAreaFragment_to_balanceUpFragment)
        }





        val recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
        val listData = listOf(
            ModelPersonalArea(R.drawable.ic_path, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.ic_union, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.ic_union, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.ic_path, "Moscow→Paris", "10:00", "-1 296 288 ₽"),

            ModelPersonalArea(R.drawable.ic_path, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.ic_union, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.ic_union, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.ic_path, "Moscow→Paris", "10:00", "-1 296 288 ₽"),
            ModelPersonalArea(R.drawable.ic_path, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.ic_union, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.ic_union, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.ic_path, "Moscow→Paris", "10:00", "-1 296 288 ₽"),
        )
        val customAdapter = PersonalAreaAdapter(requireContext(), listData)
        recycler.adapter = customAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

//    override fun onStart() {
//        super.onStart()
//        fragmentStatusViewModel.statusChecker(true)
//    }


    override fun onDestroy() {
        super.onDestroy()
        bindingPersonalAreaFragment=null
    }
}
