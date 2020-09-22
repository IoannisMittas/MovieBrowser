package com.mittas.moviebrowser.data.network.movies

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MoviesRemoteApi {

    @GET("movie-offers")
    fun getMovieOffers(): Single<MovieOffersResponse>

    @GET("movie-data")
    fun getMovieData(): Single<List<MovieData>>
}