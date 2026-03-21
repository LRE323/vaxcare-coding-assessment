package com.example.vaxcare.views

import androidx.compose.foundation.clickable
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
import com.example.vaxcare.ListScreenIntent
import com.example.vaxcare.ListScreenIntent.OnBookSelected
import com.example.vaxcare.ListScreenUiState
import com.example.vaxcare.R
import com.example.vaxcare.models.Book

@Composable
fun BookListScreen(
    uiState: ListScreenUiState,
    onIntent: (ListScreenIntent) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            uiState.bookList?.let { bookList ->
                BookLazyColumn.List(bookList) { selectedBook ->
                    onIntent(OnBookSelected(selectedBook))
                }
            }
        }
    }
}

object BookLazyColumn {

    @Composable
    fun List(
        bookList: List<Book>,
        onClickBook: (Book) -> Unit
    ) {
        LazyColumn {
            items(count = bookList.size) {
                val currentBook = bookList[it]
                BookItem(currentBook) {
                    onClickBook(currentBook)
                }
            }
        }
    }

    @Composable
    private fun BookItem(
        book: Book,
        onClickBook: () -> Unit
    ) {
        Column(
            Modifier.clickable{ onClickBook() }
        ) {
            Spacer(Modifier.height(16.dp))
            BookTitle(book)
            Spacer(Modifier.height(8.dp))
            BookAuthor(book)
            Spacer(Modifier.height(8.dp))
            BookStatus(book)
            Spacer(Modifier.height(16.dp))
            HorizontalDivider()
        }
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
}