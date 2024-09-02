package com.awesome.newsapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeAgo(isoDate: String): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val dateTime = ZonedDateTime.parse(isoDate, formatter)
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