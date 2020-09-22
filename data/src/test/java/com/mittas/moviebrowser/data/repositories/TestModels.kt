package com.mittas.moviebrowser.data.repositories

import com.mittas.moviebrowser.data.network.movies.MovieDataResponse
import com.mittas.moviebrowser.data.network.movies.MovieOffersResponse

val movieData1 = MovieDataResponse.MovieData(1, "Ocean's eleven", "First installment")
val movieOffer1 = MovieOffersResponse.MovieOffer(
    movieId = 1,
    price = "30.00€",
    imageUrlPath = "/losAngeles.png",
    isAvailable = true
)

val movieData2 = MovieDataResponse.MovieData(2, "Harry potter", "The philosophers stone")
val movieOffer2 = MovieOffersResponse.MovieOffer(
    movieId = 2,
    price = "40.00€",
    imageUrlPath = "/london.png",
    isAvailable = false
)