package com.plcoding.bookpedia.core.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


sealed interface UiText {
    /* All the UI Text in this app will either be
     - a Dynamic String, which is just like a regular String, or
     - a StringResourceId, which is just a reference to the string resource
     this is useful for when we want to localize the text.

     This is just to make working with string resource ids
      wrapping and unwrapping text easier

     */
    data class DynamicString(val value: String): UiText
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): UiText

    // This is used to unwrap the UiText class back to a string
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }
}