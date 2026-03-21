package com.example.vaxcare

import com.example.vaxcare.models.Book

interface BookRepository {

    suspend fun fetchBookList(): List<Book>
}