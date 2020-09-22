package com.mittas.moviebrowser.data.network.movies

import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("movie_data") val movieDataList: List<MovieData>

) {

    data class MovieData(
        @SerializedName("movie_id") val movieId: Int?,
        val title: String?,
        @SerializedName("sub_title") val subtitle: String?
    )
}