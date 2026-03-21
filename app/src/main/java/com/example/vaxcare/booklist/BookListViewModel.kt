package com.example.vaxcare.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaxcare.models.Book
import com.example.vaxcare.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val uiState = MutableStateFlow(ListScreenUiState())

    private val _detailsScreenNavigationTrigger = MutableSharedFlow<Book>()
    val detailsScreenNavigationTrigger: SharedFlow<Book> get() = _detailsScreenNavigationTrigger

    init {
        fetchBooks()
    }

    fun onIntent(listScreenIntent: ListScreenIntent) {
        when(listScreenIntent) {
            is ListScreenIntent.OnBookSelected -> { onBookSelected(listScreenIntent.book) }
        }
    }

    private fun onBookSelected(book: Book) {
        viewModelScope.launch {
            _detailsScreenNavigationTrigger.emit(book)
        }
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            handleLoading(true)

            delay(1000) // adding delay demo loading indicator

            uiState.update {
                it.copy(
                    bookList = bookRepository.fetchBookList()
                )
            }

            handleLoading(false)
        }
    }

    private fun handleLoading(shouldShowLoading: Boolean) {
        uiState.update {
            it.copy(shouldShowLoading = shouldShowLoading)
        }
    }

}

data class ListScreenUiState(
    val screenTitle: String = "",
    val shouldShowLoading: Boolean = false,
    val bookList: List<Book>? = null
)

sealed interface ListScreenIntent {
    data class OnBookSelected(val book: Book): ListScreenIntent
}