package com.example.vaxcare.repository

import com.example.vaxcare.models.Book

interface BookRepository {

    suspend fun fetchBookList(): List<Book>

    suspend fun findBook(id: Int): Book?
}