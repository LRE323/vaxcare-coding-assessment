package com.example.vaxcare

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            Text(text = viewModel.title)
        }
    }
}