package com.example.vaxcare.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vaxcare.room.DatabaseConstants
import com.example.vaxcare.room.DatabaseConstants.CACHE_KEY_BOOK_LIST

@Entity(tableName = DatabaseConstants.CACHE_METADATA_TABLE)
data class BookCacheMetadata(
    @PrimaryKey val key: String = CACHE_KEY_BOOK_LIST,
    val lastFetchedTimestamp: Long
)