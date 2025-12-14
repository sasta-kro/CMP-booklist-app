package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.Book


/*
This is where all the actions the user could perform on the screen is written
 */
sealed interface BookListAction {
    /*
    The action classes should be named in a way where it describes what the user did
    rather than what should happen after the click because the UI is not responsible
    on deciding what happen, that is the responsibility of the View Model.

    This keeps the UI and the ViewModel decoupled, making it easier to reuse it.


    */
    data class OnSearchQueryChange(val query: String): BookListAction
    data class OnBookClick(val book: Book): BookListAction
    data class OnTabSelected(val index: Int): BookListAction

}
