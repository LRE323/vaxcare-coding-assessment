package com.example.vaxcare

import com.example.vaxcare.models.Book
import com.example.vaxcare.network.BookApi
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
) : BookRepository {
    override suspend fun fetchBookList(): List<Book> {
        return api.fetchBookList()
    }
}