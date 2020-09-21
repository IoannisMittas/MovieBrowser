package com.mittas.moviebrowser.data.repositories.movies

import com.mittas.moviebrowser.data.network.movies.MoviesRemoteApi
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.repository.movies.MoviesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesRemoteApi: MoviesRemoteApi) : MoviesRepository {

    override fun getMovies(): Single<List<Movie>> {
        TODO("Not yet implemented")
    }
}