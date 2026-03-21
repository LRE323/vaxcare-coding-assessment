package com.example.vaxcare.room

import androidx.room.TypeConverter
import com.example.vaxcare.models.BookStatus
import kotlinx.serialization.json.Json

class BookStatusConverter {
    @TypeConverter
    fun fromBookStatus(status: BookStatus): String {
        return Json.encodeToString(status)
    }

    @TypeConverter
    fun toBookStatus(json: String): BookStatus {
        return Json.decodeFromString(json)
    }
}