package com.mvpvn.jetpackcomposedemo.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.mvpvn.jetpackcomposedemo.R
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(timeFormat: String = TimeFormat.HH_MM): String {
    return SimpleDateFormat(timeFormat).format(Date())
}

fun getCurrentTimeLater(
    timeFormat: String = TimeFormat.HH_MM_a,
    minuteLater: Int = 30
): String {
    val correctMinuteLater = if (minuteLater <= 0) 30 else minuteLater
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MINUTE, correctMinuteLater)

    val dateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
    return dateFormat.format(calendar.time)
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertLocalDateToFormat(
    localDate: LocalDate,
    format: String = TimeFormat.YYYYMMDDHHMMSS
): String {
    val localDateTime = LocalDateTime.of(localDate, LocalDateTime.MIN.toLocalTime())
    val formatter = DateTimeFormatter.ofPattern(format)
    return localDateTime.format(formatter)
}

fun abbreviateDayOfWeek(context: Context, dayOfWeek: String): String {
    val daysMap = mapOf(
        context.getString(R.string.monday) to "MO",
        context.getString(R.string.tuesday) to "TU",
        context.getString(R.string.wednesday) to "WE",
        context.getString(R.string.thursday) to "TH",
        context.getString(R.string.friday) to "FR",
        context.getString(R.string.saturday) to "SA",
        context.getString(R.string.sunday) to "SU"
    )

    return daysMap[dayOfWeek] ?: "Unk"
}

fun getFormattedDatesOfMonth(): List<String> {
    val dateFormatter = DateTimeFormatter.ofPattern(TimeFormat.DD_EEEE_YYYY, Locale.getDefault())
    val currentDate = LocalDate.now()

    val startOfMonth = currentDate.withDayOfMonth(1)
    val endOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth())

    return (startOfMonth.dayOfMonth..endOfMonth.dayOfMonth).map { dayOfMonth ->
        startOfMonth.withDayOfMonth(dayOfMonth).format(dateFormatter)
    }
}

fun getRangeOfHour(): List<String> {
    return (0..23).map { hour ->
        String.format("%02d:00", hour)
    }
}

object TimeFormat {
    const val DD_EEEE_YYYY = "dd EEEE yyyy"
    const val EEEE_D = "EEEE/d"
    const val D_MMMM_YYYY = "d MMMM yyyy"
    const val MMMM_YYYY = "MMMM yyyy"
    const val HH_MM_a = "HH:mm a"
    const val HH_MM = "HH:mm"
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
