package com.plcoding.bookpedia.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Required for the 'by' keyword delegate
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.bookpedia.book.domain.Book
import org.koin.compose.viewmodel.koinViewModel

/**
 * BookListScreenRoot (The navigation manager component)
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
      This is a default argument using the Koin Dependency Injection library.

      Functionality: If the caller does not provide a viewModel instance, Koin automatically
      searches the dependency graph, finds the correct BookListViewModel, and injects it here.

      Benefit: It allows for easy testing (a fake ViewModel can be passed manually) while
      keeping the production call site clean.
     */
    viewModel: BookListViewModel = koinViewModel(),

    /*
      `onBookClick:`
      A callback function (lambda parameter) which will be provided by the Navigation Host
      (the component that handles all the UI screens). In this simple project, it will be the
      App module inside commonMain.

      The Screen itself does not know HOW to navigate to another screen.
      It simply invokes/calls this function when a book is selected,
      and the Navigation Host component will handle what will happen
      like the actual screen transition to another screen.
     */
    onBookClick: (Book) -> Unit,
) {
    /*
      viewModel.state.collectAsStateWithLifecycle():
      1. `viewModel.state` - Accesses the public StateFlow variable `state` from the ViewModel.

      2. `collectAsStateWithLifecycle()`: Safely converts the reactive state Flow (stream of data)
      into a Compose State (snapshot of data).
      It is "Android Lifecycle Aware"  (eg, active/foreground, background, destroyed)
      meaning it stops listening when the app goes to the background to save battery.

      3. `by`: A Kotlin delegate that allows accessing `state` (the new one created in this file,
       not the viewModel's one) directly as a BookListState object
      instead of needing to type `state.value` every time.
     */
    val state by viewModel.state.collectAsStateWithLifecycle()

    /*  Passing data down to the "UI Drawer" component.
         This pattern is called "State Hoisting".
     */
    BookListScreen(
        state = state, // Passes the current data snapshot to be drawn.

        /*
          onAction Lambda (event catcher):
          Catches events from the UI (BookListScreen) when it triggers its onAction param-function
          'action' is the specific event object that was defined in BookListActions
          (OnBookClick, OnSearchQueryChange, etc).
         */
        onAction = { action ->
            when (action) {
                /*
                  Case 1: Navigation Event
                  If the action is clicking a book, this component handles it directly
                  by calling the navigation callback `onBookClick`.
                  It is NOT sent to the ViewModel because navigation is a UI-side concern.

                  It is basically like if the UI drawer (BookListScreen) calls `onAction`,
                  then let the root (BookListScreenRoot) handle it.

                  root pov: if the action is `OnBookClick` then I will call my
                  `onBookClick` param-function and let the function that calls me (parent) handles it.
                  In this case the commonMain `App` component will call this and handle it.

                  These "handing it over" are called "Bubbling"

                 */
                is BookListAction.OnBookClick -> onBookClick(action.book)


                else -> Unit  // not finished yet
            }
        }
    )
}

/**
 * BookListScreen (The UI Drawer Component)
 *
 * Responsibilities:
 * 1. Pure UI rendering. It takes data (`state` from BookListScreenRoot) and draws it.
 * 2. User Interaction detection. When a user touches something, it fires `onAction`.
 *
 * It is `private` because it should only be accessed via the Root manager component.
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
    // Actual UI implementation
    // Eg: When the user types "A", this component calls: onAction(BookListAction.OnSearchQueryChange("A"))






















}









