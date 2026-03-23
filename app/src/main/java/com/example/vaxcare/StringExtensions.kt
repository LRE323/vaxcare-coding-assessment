package com.example.vaxcare

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a")

fun String.toDisplayableBookTime(): String {
    return try {
        if (this.contains("Z")) { // Has UTC offset
            val instant = Instant.parse(this)
            val local = instant.atZone(ZoneId.systemDefault())
            local.format(dateTimeFormatter)
        } else {
            val dateTime = LocalDateTime.parse(this)
            dateTime.format(dateTimeFormatter)
        }
    } catch (_: Exception) {
        this
    }
}