package com.example.vaxcare

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vaxcare.models.Book

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    uiState: ListScreenUiState = viewModel.uiState.collectAsStateWithLifecycle().value
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            uiState.bookList?.let { books ->
                BookLazyColumn.List(books)
            }
        }
    }
}

object BookLazyColumn {

    @Composable
    private fun BookItem(book: Book) {
        BookTitle(book)
        Spacer(Modifier.height(8.dp))
        BookAuthor(book)
        Spacer(Modifier.height(8.dp))
        BookStatus(book)
    }

    @Composable
    private fun BookStatus(book: Book) {
        val status = stringResource(R.string.book_display_text_label, book.status.displayText)
        Text(status)
    }

    @Composable
    private fun BookTitle(book: Book) {
        val text = stringResource(R.string.book_title_label, book.title)
        Text(text)
    }

    @Composable
    private fun BookAuthor(book: Book) {
        val text = stringResource(R.string.book_author_label, book.author)
        Text(text)
    }

    @Composable
    fun List(
        bookList: List<Book>
    ) {
        LazyColumn {
            items(count = bookList.size) {
                Spacer(Modifier.height(16.dp))
                BookItem(bookList[it])
                Spacer(Modifier.height(16.dp))
                HorizontalDivider()
            }
        }
    }
}