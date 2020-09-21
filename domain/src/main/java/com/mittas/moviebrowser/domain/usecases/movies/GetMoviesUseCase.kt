package com.mittas.moviebrowser.domain.usecases.movies

import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.repository.movies.MoviesRepository
import com.mittas.moviebrowser.domain.usecases.BaseRxUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @Named("workScheduler") override val workScheduler: Scheduler,
    @Named("postScheduler") override val postScheduler: Scheduler
) : BaseRxUseCase() {

    fun get(): Single<List<Movie>> =
        moviesRepository.getMovies()
            .subscribeOn(workScheduler)
            .observeOn(postScheduler)
}