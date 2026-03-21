package com.example.vaxcare.repository

import com.example.vaxcare.models.Book
import com.example.vaxcare.models.BookCacheMetadata
import com.example.vaxcare.network.BookApi
import com.example.vaxcare.room.BookDao
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
    private val bookDao: BookDao
) : BookRepository {
    private val maxCacheAge = 1.hours.inWholeMilliseconds

    @Throws(Exception::class)
    override suspend fun fetchBookList(): List<Book> {
        return if (cacheIsValid(bookDao.getMetadata())) {
            println("Getting data from cache...")
            bookDao.getAllBooks()
        } else {
            fetchBookListFromApi()
        }
    }

    @Throws(Exception::class)
    private suspend fun fetchBookListFromApi(): List<Book> {
        return try {
            println("Fetching book list from API...")
            val response = api.fetchBookList()
            bookDao.clearAll()
            bookDao.insertAll(response)
            bookDao.insertMetadata(
                metadata = BookCacheMetadata(lastFetchedTimestamp = System.currentTimeMillis())
            )
            bookDao.getAllBooks()
        } catch (_: Exception) {
            // Try to fall back on existing cache data in case of any network errors
            println("Exception occurred, attempting to fall back on existing cache data...")
            val cache = bookDao.getAllBooks()
            cache.ifEmpty {
                // No existing cache to fall back on, throw exception
                throw Exception("No book list cache data stored")
            }
        }
    }

    /**
     * Cache is valid if it is non-null and less than the max cache age.
     */
    private fun cacheIsValid(cache: BookCacheMetadata?): Boolean {
        if (cache == null) return false
        val age = System.currentTimeMillis() - cache.lastFetchedTimestamp
        return age < maxCacheAge
    }
}