package com.example.vaxcare.network

import com.example.vaxcare.models.Book
import retrofit2.http.GET

interface BookApi {

    @GET(BookApiEndPoints.BOOK_LIST)
    suspend fun fetchBookList(): List<Book>
}

private object BookApiEndPoints {
    const val BOOK_LIST = "android-test-vaxcare/27bd7ab7d0381f867723225145694e93/raw/" +
            "c530190f575aaac1ab8d5c416b2da9553be422fe/local-database2.json"
}