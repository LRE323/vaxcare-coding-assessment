package com.example.vaxcare.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vaxcare.models.Book
import com.example.vaxcare.models.BookCacheMetadata
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM ${DatabaseConstants.BOOK_TABLE}")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM ${DatabaseConstants.BOOK_TABLE} WHERE id = :id")
    suspend fun getBookById(id: Int): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<Book>)

    @Query("DELETE FROM ${DatabaseConstants.BOOK_TABLE}")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetadata(metadata: BookCacheMetadata)

    @Query("SELECT * FROM ${DatabaseConstants.CACHE_METADATA_TABLE} WHERE `key` = '${DatabaseConstants.CACHE_KEY_BOOK_LIST}'")
    suspend fun getMetadata(): BookCacheMetadata?
}