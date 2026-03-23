package com.example.vaxcare.bookdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vaxcare.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUiState) {
    Scaffold { paddingValues ->
        Column(Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)) {
            Title(uiState.title, Modifier.align(Alignment.CenterHorizontally))
            Spacer(Modifier
                .height(16.dp)
                .fillMaxWidth())
            Author(uiState.author)

            Spacer(Modifier
                .height(16.dp)
                .fillMaxWidth())

            StatusDisplayText(uiState.statusDisplayText)
            TimeCheckedIn(uiState.timeCheckedIn)
            TimeCheckedOut(uiState.timeCheckedOut)
            DueDate(uiState.dueDate)

            Spacer(Modifier
                .height(16.dp)
                .fillMaxWidth())

            LastEdited(uiState.lastEdited)
            Fee(uiState.fee)

            if (uiState.shouldShowErrorMessage) {
                BookLoadingFailedMessaged(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun Title(bookTitle: String?, modifier: Modifier) {
    bookTitle?.let {
        Text(
            text = it,
            modifier = modifier,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun Author(authors: String?) {
    authors?.let {
        val authorLabel = stringResource(R.string.book_author_label, it)
        Text(
            text = authorLabel,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun StatusDisplayText(status: String?) {
    status?.let {
        Text(
            text = stringResource(R.string.book_display_text_label, it),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun TimeCheckedIn(time: String?) {
    time?.let {
        Text(
            text = stringResource(R.string.time_checked_in_label, it),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun TimeCheckedOut(time: String?) {
    time?.let {
        Text(
            text = stringResource(R.string.time_checked_out_label, it),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun DueDate(dueDate: String?) {
    dueDate?.let {
        Text(
            text = stringResource(R.string.due_date_label, it),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun Fee(fee: String?) {
    fee?.let {
        Text(
            text = stringResource(R.string.fee_label, it),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun LastEdited(lastEdited: String?) {
    lastEdited?.let {
        Text(
            text = stringResource(R.string.last_edited_label, it),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun BookLoadingFailedMessaged(modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.book_detail_load_failed_error),
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = ":(",
            style = MaterialTheme.typography.titleSmall
        )
    }
}