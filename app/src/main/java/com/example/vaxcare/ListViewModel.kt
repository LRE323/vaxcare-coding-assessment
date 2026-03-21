package com.example.vaxcare

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class ListViewModel: ViewModel() {
    val uiState = MutableStateFlow(ListScreenUiState())
}

data class ListScreenUiState(
    val screenTitle: String = "",
    val shouldShowLoading: Boolean = false
)