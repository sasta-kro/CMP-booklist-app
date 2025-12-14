package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.UiText


/* All kinds of values that could change by user interaction and have an effect on
the UI will go in here

This is basically part of the View Model but it is separated into its own data class to
make it cleaner

 */
data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> =  emptyList(), // empty list as placeholder
    val favoriteBook: List<Book> = emptyList(),
    val isLoading: Boolean = false, // this is to show the user a loading screen

    // the app will have 2 tabs, so this is to keep track on which tab is selected/displayed
    val selectedTabIndex: Int = 0,

    // this is to show an error message in case something went wrong
    // nullable because, usually there shouldn't be any errors
    val errorMessage: UiText? = null

)

