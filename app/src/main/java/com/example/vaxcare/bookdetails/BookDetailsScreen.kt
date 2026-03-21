package com.example.vaxcare.bookdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookDetailsScreen() {
    Scaffold { paddingValues ->
        Column(Modifier.padding(paddingValues).padding(16.dp)) {
            Text("BOOK DETAILS SCREEN")
        }
    }
}