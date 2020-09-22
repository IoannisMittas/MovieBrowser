package com.mittas.moviebrowser.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.usecases.movies.GetMoviesUseCase
import com.mittas.moviebrowser.ui.utils.Resource
import com.mittas.moviebrowser.ui.utils.setError
import com.mittas.moviebrowser.ui.utils.setLoading
import com.mittas.moviebrowser.ui.utils.setSuccess
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movies: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    val movies: LiveData<Resource<List<Movie>>>
        get() = _movies

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        compositeDisposable.add(
            getMoviesUseCase.get()
                .doOnSubscribe { _movies.setLoading() }
                .subscribe({ movies ->
                    _movies.setSuccess(movies)
                }, { t ->
                    _movies.setError(t.message)
                    Timber.d(t, "Error on fetching movies.")
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}