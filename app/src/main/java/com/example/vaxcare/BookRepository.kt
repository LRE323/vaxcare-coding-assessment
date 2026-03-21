package com.example.vaxcare

interface BookRepository {

    suspend fun fetchBookList()
}