package com.example.vaxcare

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    uiState: ListScreenUiState = viewModel.uiState.collectAsStateWithLifecycle().value
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
        }
    }
}