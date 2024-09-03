package com.awesome.newsapp.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.Duration


@RequiresApi(Build.VERSION_CODES.O)
fun formatIsoDateToDateTime(isoDate: String): ZonedDateTime {
    return try {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        ZonedDateTime.parse(isoDate, formatter)
    } catch (e: Exception) {
        ZonedDateTime.now()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeAgo(dateTime: ZonedDateTime): String {
    val now = ZonedDateTime.now()
    val duration = Duration.between(dateTime, now)

    return when {
        duration.toMinutes() < 1 -> "just now"
        duration.toMinutes() < 60 -> "${duration.toMinutes()} minutes ago"
        duration.toHours() < 24 -> "${duration.toHours()} hours ago"
        duration.toDays() < 7 -> "${duration.toDays()} days ago"
        duration.toDays() < 30 -> "${duration.toDays() / 7} weeks ago"
        duration.toDays() < 365 -> "${duration.toDays() / 30} months ago"
        else -> "${duration.toDays() / 365} years ago"
    }
}