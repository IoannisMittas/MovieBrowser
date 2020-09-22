package com.mittas.moviebrowser.data.network.movies

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("movie_id") val movieId: Int?,
    val title: String?,
    @SerializedName("sub_title") val subtitle: String?
)