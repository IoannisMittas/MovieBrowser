package com.mittas.moviebrowser.domain.repository.movies

import com.mittas.moviebrowser.domain.entity.movies.Movie
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {

    fun getMovies(): Single<List<Movie>>
}