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
        fetchBooks(this::handleProgressIndicator)
    }

    fun onIntent(listScreenIntent: ListScreenIntent) {
        when(listScreenIntent) {
            is ListScreenIntent.OnBookSelected -> { onBookSelected(listScreenIntent.book) }
            ListScreenIntent.RefreshList -> { refreshList() }
        }
    }

    private fun handleIsRefreshing(isRefreshing: Boolean) {
        uiState.update {
            it.copy(isRefreshing = isRefreshing)
        }
    }

    private fun refreshList() {
        fetchBooks { handleIsRefreshing(it) }
    }

    private fun onBookSelected(book: Book) {
        viewModelScope.launch {
            _detailsScreenNavigationTrigger.emit(book)
        }
    }

    private fun fetchBooks(
        handleLoading: (Boolean) -> Unit
    ) {
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

    private fun handleProgressIndicator(shouldShow: Boolean) {
        uiState.update {
            it.copy(shouldShowProgressIndicator = shouldShow)
        }
    }

}

data class ListScreenUiState(
    val screenTitle: String = "",
    val shouldShowProgressIndicator: Boolean = false,
    val bookList: List<Book>? = null,
    val isRefreshing: Boolean = false
)

sealed interface ListScreenIntent {
    data class OnBookSelected(val book: Book): ListScreenIntent
    data object RefreshList: ListScreenIntent
}