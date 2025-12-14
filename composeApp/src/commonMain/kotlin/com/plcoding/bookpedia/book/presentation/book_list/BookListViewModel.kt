package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Every ViewModel in Compose inherits from ...lifecycle.ViewModel lib
class BookListViewModel: ViewModel() {

    /* These are the variables that stores the state of the BookList screen.
    The tutorial guy prefers to use MutableStateFlow class to use as state.
    We pass in our BookListState class which has all the possible changing variables of the screen

    A good practice is to have 2 state variables:
    - a private state variable that will be changing inside the ViewModel
    - an immutable public state model that can be accessed publicly
    this is because the UI shouldn't be able to mutate the state directly
    it should be done by the View Model

     */
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    // The UI should call this function that pass-in the action
    // the View Model then should update the state
    fun onAction(action: BookListAction) {
        when(action){
            is BookListAction.OnBookClick -> TODO()

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    // it basically safe sets the new searchQuery value to update state
                    it.copy(searchQuery = action.query)

                    /* I can think of something like
                    _state.BookListState.searchQuery = action.newQuery
                     */
                }
            }

            is BookListAction.OnTabSelected -> {
                _state.update{
                    it.copy(selectedTabIndex = action.index) // updates the tab index
                }
            }
        }

    }
}