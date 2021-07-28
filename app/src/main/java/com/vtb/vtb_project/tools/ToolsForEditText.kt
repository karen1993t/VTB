package com.vtb.vtb_project.tools

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.text.SimpleDateFormat
import java.util.*

object ToolsForEditText {
    private lateinit var calendar: Calendar
    private lateinit var currentFormatDate: String


    fun createDateDialog(context: Context, formatDate: String, editDateText: EditText) {
        calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(formatDate, editDateText)
            }
        val dialog = DatePickerDialog(
            context, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    //  myFormat = "dd.MM.yyyy"
    private fun updateDateInView(formatDate: String, editDateText: EditText) {
        val simpleDateFormat = SimpleDateFormat(formatDate, Locale.US)
        currentFormatDate = simpleDateFormat.format(calendar.time)
        editDateText.setText(currentFormatDate)
    }

    fun createMaskEdit(index: Int, array: Array<String>, editText: EditText) {
        val slots =
            PhoneNumberUnderscoreSlotsParser().parseSlots(array[index])
        val maskInput = MaskImpl.createTerminated(slots)
        val formatWatcher = MaskFormatWatcher(maskInput)
        formatWatcher.installOn(editText)
    }
}
