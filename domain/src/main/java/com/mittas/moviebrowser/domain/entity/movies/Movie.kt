package com.mittas.moviebrowser.domain.entity.movies

data class Movie(
    val id: String,
    val title: String,
    val subTitle: String,
    val price: Float,
    val imageUrl: String?,
    val isAvailable: Boolean
)