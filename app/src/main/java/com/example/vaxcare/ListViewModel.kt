package com.example.vaxcare

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ListViewModel: ViewModel() {

    val title = "LIST SCREEN"
}