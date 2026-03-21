package com.example.vaxcare

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val uiState = MutableStateFlow(ListScreenUiState())
}

data class ListScreenUiState(
    val screenTitle: String = "",
    val shouldShowLoading: Boolean = false
)