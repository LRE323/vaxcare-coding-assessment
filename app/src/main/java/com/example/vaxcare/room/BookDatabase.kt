package com.example.vaxcare.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vaxcare.models.Book
import com.example.vaxcare.models.BookCacheMetadata

@Database(
    entities = [Book::class, BookCacheMetadata::class],
    version = 1
)
@TypeConverters(BookStatusConverter::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}