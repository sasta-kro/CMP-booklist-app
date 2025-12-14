package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Every ViewModel in Compose inherits from ...lifecycle.ViewModel lib
class BookListViewModel: ViewModel() {

    /* These (_state & state) are the variables that stores the state of the BookList screen.
    This is the "State Holder" pattern, it initializes the BookListState() object.
    The tutorial guy prefers to use MutableStateFlow class to use as state.
    We pass in our BookListState class which has all the possible changing variables of the screen

    A good practice is to have 2 state variables:
    - a private state variable that will be changing inside the ViewModel
    - an immutable read-only public state model that can be accessed publicly
    this public state is not a new variable, it is a reference to the private variable (a live link)
    It's like a read-only mirror. Any changes to _state is immediately visible through `state`
    because they are linked by the `asStateFlow()` operator. It is a wrapper, not a copy.

    this is because the UI shouldn't be able to mutate the state directly
    it should be done by the View Model



     */
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()  // sets the reference to _state

    // The UI should call this function that pass-in the action
    // the View Model then should update the private _state
    // then the public `state` variable will automatically reflect the change via asStateFlow()
    fun onAction(action: BookListAction) {   // a book list action as argument
        // `when` case must be exhaustive
        when(action){

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    // it basically safely sets the new searchQuery value to update state
                    it.copy(searchQuery = action.query)

                    /* I can think of something like
                    _state.BookListState.searchQuery = action.newQuery

                    We cannot do this because a data class (used for BookListState) is immutable
                    by default. The only way to change the state is to create a new state object
                    using the `copy()` function.

                    `it` = the current `_state` variable (because we used _state.update{...})
                    so, it is like `_state.copy(...)` copy the whole thing,
                    but change/update this value `searchQuery = action.query` when copying a new object.

                     */
                }
            }

            is BookListAction.OnBookClick -> TODO()


            is BookListAction.OnTabSelected -> {
                _state.update{
                    it.copy(selectedTabIndex = action.index) // updates the tab index
                }
            }
        }

    }
}