package com.example.vaxcare.bookdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaxcare.models.Book
import com.example.vaxcare.repository.BookRepository
import com.example.vaxcare.toDisplayableBookTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val repository: BookRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val uiState = MutableStateFlow(BookDetailsUiState())
    private val bookId: Int = savedStateHandle["bookId"] ?: 0

    init {
        fetchBookDetails()
    }

    private fun fetchBookDetails() {
        viewModelScope.launch {
            val book = repository.findBook(bookId)

            if (book != null) {
                updateUiStateWithBookDetails(book)
            } else {
                uiState.update {
                    it.copy(shouldShowErrorMessage = true)
                }
            }
        }
    }

    private fun updateUiStateWithBookDetails(book: Book) {
        uiState.update {
            it.copy(
                title = book.title,
                author = book.author,
                statusDisplayText = book.status.displayText,
                timeCheckedIn = book.status.timeCheckedIn?.toDisplayableBookTime(),
                timeCheckedOut = book.status.timeCheckedOut?.toDisplayableBookTime(),
                dueDate = book.status.dueDate?.toDisplayableBookTime(),
                fee = book.fee.toString(),
                lastEdited = book.lastEdited.toDisplayableBookTime()
            )
        }
    }
}


data class BookDetailsUiState(
    val title: String? = null,
    val author: String? = null,
    val statusDisplayText: String? = null,
    val timeCheckedIn: String? = null,
    val timeCheckedOut: String? = null,
    val dueDate: String? = null,
    val fee: String? = null,
    val lastEdited: String? = null,
    val shouldShowErrorMessage: Boolean = false
)