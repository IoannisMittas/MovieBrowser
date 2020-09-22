package com.mittas.moviebrowser.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.usecases.movies.GetMoviesUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>>
        get() = _movies

    // todo check this
    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        compositeDisposable.add(
            getMoviesUseCase.get()
                .subscribe({ movies ->
                    _movies.postValue(movies)
                }, { t ->
                    Timber.d(t, "Error on fetching movies.")
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}