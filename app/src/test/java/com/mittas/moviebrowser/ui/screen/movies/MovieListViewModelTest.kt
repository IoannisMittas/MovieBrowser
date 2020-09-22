package com.mittas.moviebrowser.ui.screen.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.usecases.movies.GetMoviesUseCase
import com.mittas.moviebrowser.ui.utils.Resource
import com.mittas.moviebrowser.ui.utils.ResourceState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieListViewModelTest {

    private lateinit var viewModel: MovieListViewModel

    private val mockGetMoviesUseCase: GetMoviesUseCase = mock()

    private val moviesExpected: List<Movie> = listOf(
        Movie(
            id = 5,
            price = "30.00€",
            imageUrl = "www.example.com",
            isAvailable = false
        ).apply {
            title = "Saving Private ryan"
            subTitle = "Normandy"
        }
    )

    private val throwable = Throwable()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getMoviesSuccess() {
        whenever(mockGetMoviesUseCase.get()).thenReturn(Single.just(moviesExpected))

        viewModel = MovieListViewModel(mockGetMoviesUseCase)
        viewModel.fetchMovies()

        // One time called at init, another by explicit call
        verify(mockGetMoviesUseCase, times(2)).get()

        Assert.assertEquals(
            Resource(
                state = ResourceState.SUCCESS,
                data = moviesExpected,
                message = null
            ),
            viewModel.movies.value
        )
    }

    @Test
    fun getMovieFailure() {
        whenever(mockGetMoviesUseCase.get()).thenReturn(Single.error(throwable))

        viewModel = MovieListViewModel(mockGetMoviesUseCase)
        viewModel.fetchMovies()

        // One time called at init, another by explicit call
        verify(mockGetMoviesUseCase, times(2)).get()

        Assert.assertEquals(
            Resource(
                state = ResourceState.ERROR,
                data = null,
                message = throwable.message
            ),
            viewModel.movies.value
        )
    }
}