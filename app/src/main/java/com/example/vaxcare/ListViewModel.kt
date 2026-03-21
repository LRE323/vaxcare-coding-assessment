package com.example.vaxcare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaxcare.models.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val uiState = MutableStateFlow(ListScreenUiState())

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        handleLoading(true)

        viewModelScope.launch {

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