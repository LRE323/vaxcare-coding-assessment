package com.example.vaxcare.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.vaxcare.room.BookStatusConverter
import com.example.vaxcare.room.DatabaseConstants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = DatabaseConstants.BOOK_TABLE)
@TypeConverters(BookStatusConverter::class)
data class Book(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val status: BookStatus,
    val fee: Float,
    val lastEdited: String
)