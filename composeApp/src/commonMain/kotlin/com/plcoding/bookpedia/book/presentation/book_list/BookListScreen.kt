package com.plcoding.bookpedia.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Required for the 'by' keyword delegate
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.bookpedia.book.domain.Book
import org.koin.compose.viewmodel.koinViewModel

/**
 * BookListScreenRoot (The "Smart" Manager Component)
 *
 * Responsibilities:
 * 1. integration point between the UI and the System (ViewModel, Navigation).
 * 2. Collects the State Flow and converts it into a UI Snapshot.
 * 3. Handles "Traffic Control" for actions: decides if an action should trigger navigation
 * or if it should be sent to the ViewModel for processing.
 *
 * It does NOT draw pixels (UI elements). It manages data flow.
 */
@Composable
fun BookListScreenRoot(
    /*   `= koinViewModel():`
      This is a "Default Argument" using the Koin Dependency Injection library.
      Functionality: If the caller does not provide a viewModel instance, Koin automatically
      searches the dependency graph, finds the correct BookListViewModel, and injects it here.
      Benefit: It allows for easy testing (a fake ViewModel can be passed manually) while
      keeping the production call site clean.
     */
    viewModel: BookListViewModel = koinViewModel(),

    /*
      `onBookClick:`
      A callback function (lambda parameter) provided by the Navigation Host (another component)
      The Screen does not know HOW to navigate. It simply invokes this function
      when a book is selected, and the Navigation Host handles the actual screen transition.
     */
    onBookClick: (Book) -> Unit,
) {
    /*
      viewModel.state.collectAsStateWithLifecycle():
      1. `viewModel.state`: Accesses the public StateFlow from the ViewModel.
      (Note: If this highlights red, the BookListViewModel class is missing the public
      `val state = _state.asStateFlow()` property).

      2. `collectAsStateWithLifecycle()`: Safely converts the reactive Flow (stream of data)
      into a Compose State (snapshot of data). It is "Lifecycle Aware," meaning it stops
      listening when the app goes to the background to save battery.

      3. `by`: A Kotlin delegate that allows accessing `state` (the new one,
       not the viewModel's one) directly as a BookListState object
      instead of needing to type `state.value` every time.
     */
    val state by viewModel.state.collectAsStateWithLifecycle()

    /*  Passing data down to the "Dumb" component.
         This pattern is called "State Hoisting".
     */
    BookListScreen(
        state = state, // Passes the current data snapshot to be drawn.

        /*
          onAction Lambda (The Event Bridge):
          Catches events up from the UI when it triggers the onAction param-function
          'action' is the specific event object (e.g., OnBookClick, OnSearchQueryChange).
         */
        onAction = { action ->
            when (action) {
                /*
                  Case 1: Navigation Event
                  If the action is clicking a book, this component handles it directly
                  by calling the navigation callback `onBookClick`.
                  It is NOT sent to the ViewModel because navigation is a UI-side concern.
                 */
                is BookListAction.OnBookClick -> onBookClick(action.book)


                else -> Unit
            }
        }
    )
}

/**
 * BookListScreen (The "Dumb" Worker Component)
 *
 * Responsibilities:
 * 1. Pure UI rendering. It takes data (`state`) and draws it.
 * 2. User Interaction detection. When a user touches something, it fires `onAction`.
 *
 * It is `private` because it should only be accessed via the Root manager.
 * It creates a separation of concerns: This function doesn't know what a ViewModel is
 * or how navigation works. It facilitates easier UI Previews (Compose Previews)
 * because it relies on simple data classes rather than complex ViewModels.
 */
@Composable
private fun BookListScreen(
    state: BookListState,

    // the UI will call the function passed into `onAction` parameter when
    // something inside the BookListScreen triggers `onAction`.
    onAction: (BookListAction) -> Unit,
) {
    // Actual UI implementation (Scaffold, LazyColumn, TextField, etc.) goes here.
    // When the user types "A", this component calls: onAction(BookListAction.OnSearchQueryChange("A"))
}