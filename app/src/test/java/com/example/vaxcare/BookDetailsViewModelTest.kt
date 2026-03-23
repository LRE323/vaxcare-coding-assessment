package com.example.vaxcare

import androidx.lifecycle.SavedStateHandle
import com.example.vaxcare.bookdetails.BookDetailsViewModel
import com.example.vaxcare.models.Book
import com.example.vaxcare.models.BookStatus
import com.example.vaxcare.repository.BookRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookDetailsViewModelTest {

    private val mockRepository: BookRepository = mockk()
    private val mockSavedStateHandle: SavedStateHandle = SavedStateHandle(mapOf("bookId" to 1))

    @Test
    fun `fetchBookDetails, repository returns null book`() = runTest {
        coEvery {
            mockRepository.findBook(any())
        } returns null

        val subject = spyk(BookDetailsViewModel(mockRepository, mockSavedStateHandle))

        val uiState = subject.uiState.value
        Truth.assertThat(uiState.shouldShowErrorMessage).isEqualTo(true)
    }

    @Test
    fun `fetchBookDetails, repository returns non-null book`() = runTest {
        val bookStatus = BookStatus(
            id = 1,
            displayText = "OnShelf",
            timeCheckedIn = "2021-08-15T02:34",
            timeCheckedOut = null,
            dueDate = null
        )
        val book = Book(
            id = 1,
            title = "Misery",
            author = "Stephen King",
            status = bookStatus,
            fee = 5.00f,
            lastEdited = "some time"
        )
        coEvery {
            mockRepository.findBook(any())
        } returns book

        val subject = spyk(BookDetailsViewModel(mockRepository, mockSavedStateHandle))

        val uiState = subject.uiState.value

        Truth.assertThat(
            uiState.author
        ).isEqualTo(
            "Stephen King"
        )
    }

}