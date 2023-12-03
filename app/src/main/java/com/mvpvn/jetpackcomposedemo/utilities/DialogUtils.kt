package com.mvpvn.jetpackcomposedemo.utilities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun showDatePickerDialog(
    context: Context,
    initialDateString: String = "", // Format : YYYYMMDDHHMMSS
    listenerSelectedDate: (String) -> Unit
) {
    val calendar = Calendar.getInstance()
    val locale = Locale.getDefault()
    val dateFormat = SimpleDateFormat(TimeFormat.YYYYMMDDHHMMSS, locale)

    if (initialDateString.isNotEmpty()) {
        try {
            calendar.time = dateFormat.parse(initialDateString) ?: throw IllegalArgumentException("Invalid date string")
        } catch (e: ParseException) {
            e.message
        }
    }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
        val selectedCalendar = Calendar.getInstance().apply {
            set(selectedYear, selectedMonth, selectedDay)
        }
        listenerSelectedDate(dateFormat.format(selectedCalendar.time))
    }, year, month, day).show()
}

fun showTimePickerDialog(
    context: Context,
    defaultTime: Date? = null,
    listener: (Int, Int) -> Unit
) {
    val calendar = Calendar.getInstance()
    defaultTime?.let { calendar.time = it }

    val defaultHour = calendar.get(Calendar.HOUR_OF_DAY)
    val defaultMinute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(context, { _, hourOfDay, minute ->
        listener(hourOfDay, minute)
    }, defaultHour, defaultMinute, true).show()
}