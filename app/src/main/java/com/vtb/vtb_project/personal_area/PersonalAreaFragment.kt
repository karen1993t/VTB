package com.vtb.vtb_project.personal_area

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vtb.vtb_project.R
import com.vtb.vtb_project.adapters.PersonalAreaAdapter
import com.vtb.vtb_project.databinding.FragmentCommunicationBinding
import com.vtb.vtb_project.databinding.FragmentPersonalAreaBinding
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser

class PersonalAreaFragment : Fragment() {
    private lateinit var bindingPersonalAreaFragment: FragmentPersonalAreaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingPersonalAreaFragment = FragmentPersonalAreaBinding.inflate(inflater)
        return bindingPersonalAreaFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val slots = UnderscoreDigitSlotsParser().parseSlots("_ ___ ___,__")
//        val formatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
//        formatWatcher.installOn(bindingPersonalArea.titleMoney)


        val spannable = SpannableStringBuilder(bindingPersonalAreaFragment.titleMoney.text)
        spannable.setSpan(
            ForegroundColorSpan(Color.GRAY), 6, 13,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        bindingPersonalAreaFragment.titleMoney.text = spannable

//        bindingPersonalAreaFragment.pay.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_payFragment)
//        }
        bindingPersonalAreaFragment.replenish.setOnClickListener {
           Navigation.findNavController(view).navigate(R.id.action_personalAreaFragment_to_balanceUpFragment)
        }


        val recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
        val listData = listOf(
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "10:00", "-1 296 288 ₽"),

            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "10:00", "-1 296 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "21:30", "-296 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.sales, "Income", "21:30", "+2 241 288 ₽"),
            ModelPersonalArea(R.drawable.hotel, "Hilton Hotel", "20:30", "-196 435 ₽"),
            ModelPersonalArea(R.drawable.wine, "Chateau Bordeaux", "12:30", "-10 288 ₽"),
            ModelPersonalArea(R.drawable.avia, "Moscow→Paris", "10:00", "-1 296 288 ₽"),
        )
        val customAdapter = PersonalAreaAdapter(requireContext(), listData)
        recycler.adapter = customAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}
