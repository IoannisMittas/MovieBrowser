package com.mittas.moviebrowser.data.repositories.movies

import com.mittas.moviebrowser.data.network.movies.MovieOffersResponse
import com.mittas.moviebrowser.data.network.movies.MoviesRemoteApi
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.repository.movies.MoviesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesRemoteApi: MoviesRemoteApi) : MoviesRepository {

    override fun getMovies(): Single<List<Movie>> {
        return moviesRemoteApi.getMovieOffers()
            .map { it.mapToDomain() }
            .flatMap { moviesWithNoTitle ->
                moviesRemoteApi.getMovieData().map { movieDataResponse ->
                    moviesWithNoTitle.forEach { movieDomain ->
                        movieDataResponse.movieDataList.firstOrNull { it.movieId == movieDomain.id }?.let {
                            movieDomain.title = it.title ?: ""
                            movieDomain.subTitle = it.subtitle ?: ""
                        }
                    }

                    moviesWithNoTitle
                }
            }
    }
}

private fun MovieOffersResponse.mapToDomain(): List<Movie> {
    val movies = mutableListOf<Movie>()

    movieOffers?.filterNotNull()?.filter { it.price != null && it.movieId != null }?.forEach {
        val movie = Movie(
            id = it.movieId!!,
            price = it.price!!,
            imageUrl = imageBaseUrl + it.imageUrlPath,
            isAvailable = it.isAvailable
        )
        movies.add(movie)
    }

    return movies.toList()
}