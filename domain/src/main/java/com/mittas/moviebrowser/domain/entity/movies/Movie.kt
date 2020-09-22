package com.mittas.moviebrowser.domain.entity.movies

data class Movie(
    val id: String,
    val price: Float,
    val imageUrl: String?,
    val isAvailable: Boolean
) {
    lateinit var title: String
    lateinit var subTitle: String
}