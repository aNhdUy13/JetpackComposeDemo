package com.mvpvn.jetpackcomposedemo.utilities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(timeFormat: String = TimeFormat.HH_h_mm_min): String {
    return SimpleDateFormat(timeFormat).format(Date())
}

fun getCurrentTimeLater(
    timeFormat: String = TimeFormat.HH_mm_a,
    minuteLater: Int = 30
): String {
    val correctMinuteLater = if (minuteLater <= 0) 30 else minuteLater
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MINUTE, correctMinuteLater)

    val dateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
    return dateFormat.format(calendar.time)
}

object TimeFormat {
    const val D_MMMM_YYYY = "d MMMM yyyy"
    const val HH_h_mm_min = "HH h mm min"
    const val MMMM_YYYY = "MMMM yyyy"
    const val HH_mm_a = "HH:mm a"
    const val MM_DD = "MM/dd"
    const val DD_MM = "dd/MM"
    const val MM_YYYY = "MM/yyyy"
    const val YYYY = "yyyy"
    const val YYYYMM = "yyyyMM"
    const val YYYYMMDD = "yyyyMMdd"
    const val YYYY_MM_DD = "yyyy/MM/dd"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm"
    const val YYYYMMDDHHMM = "yyyyMMddHHmm"
    const val YYYYMMDDHHMMSS = "yyyyMMddHHmmss"
    const val MM_DD_HH_MM = "MM/dd HH:mm"
}
