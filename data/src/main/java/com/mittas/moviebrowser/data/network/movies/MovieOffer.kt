package com.mittas.moviebrowser.data.network.movies

import com.google.gson.annotations.SerializedName

data class MovieOffersResponse(
    @SerializedName("image_base") val imageBaseUrl: String?,
    @SerializedName("movie_offers") val movieOffers: List<MovieOffer?>?
) {

    data class MovieOffer(
        @SerializedName("movie_id") val movieId: String?,
        val price: Float?,
        @SerializedName("image") val imageUrlPath: String? ,
        @SerializedName("available") val isAvailable: Boolean
    )
}