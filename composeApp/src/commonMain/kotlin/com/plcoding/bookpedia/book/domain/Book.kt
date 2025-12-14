package com.plcoding.bookpedia.book.domain

data class Book(
    // we are basically defining whether an argument is nullable by checking the API

    // the domain layer should only contain business logic and no other 3rd party things

    val id: String,
    val title: String,
    val imageUrl: String,
    val authors: List<String>,
    val description: String?,
    val languages: List<String>,
    val firstPublishYear: String?,
    val averageRating: Double?,
    val ratingCount: Int?,
    val numbPages: Int?,
    val numEditions: Int

    /*
    since this is the book listing app, this is enough to "model" a book. But lets say, in an e-book
    reader app, the model will be completely different because the model will have to keep track of
    number of pages, contents of every page, etc.
     */
)