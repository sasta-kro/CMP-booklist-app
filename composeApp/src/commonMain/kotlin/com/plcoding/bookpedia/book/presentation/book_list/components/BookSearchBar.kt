package com.plcoding.bookpedia.book.presentation.book_list.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plcoding.bookpedia.book.presentation.book_list.BookListAction

@Composable
fun BookSearchBar(
    searchQuery: String,

    // this is for when the search query changes inside the search bar
    // the new query text value will bubble up to let the parent function handle it.
    onSearchQueryChange: (String) -> Unit,


    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange

    )
}

