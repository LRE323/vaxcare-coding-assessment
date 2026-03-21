package com.example.vaxcare

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen() {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            Text(text = "THIS IS THE LIST SCREEN")
        }
    }
}