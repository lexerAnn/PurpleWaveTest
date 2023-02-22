package com.purlewave.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getCurrentDate(): String{
    val currentDate = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun customPattern(dateTimeStr: String, pattern: String): String {
    var formatter2: DateTimeFormatter? = null
    val formatter: DateTimeFormatter?
    var dateTime: LocalDateTime? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") // formatter
        dateTime = LocalDateTime.parse(dateTimeStr, formatter) // date object
        formatter2 =
            DateTimeFormatter.ofPattern(pattern) // if you want to convert it any other format
    }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        dateTime?.format(formatter2).toString()
    } else {
        ""
    }
}