package com.mittas.moviebrowser.domain.entity.movies

data class Movie(
    val id: Int,
    val price: String,
    val imageUrl: String?,
    val isAvailable: Boolean
) {
    lateinit var title: String
    lateinit var subTitle: String
}