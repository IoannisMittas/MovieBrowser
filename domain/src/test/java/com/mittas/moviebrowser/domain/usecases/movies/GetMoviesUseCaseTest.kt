package com.mittas.moviebrowser.domain.usecases.movies


import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.domain.repository.movies.MoviesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {

    private lateinit var usecase: GetMoviesUseCase

    private val mockRepository: MoviesRepository = mock()

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

    @Before
    fun setup() {
        usecase = GetMoviesUseCase(mockRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun repositoryGetSuccess() {
        whenever(mockRepository.getMovies()).thenReturn(Single.just(moviesExpected))

        val test = usecase.get().test()

        verify(mockRepository).getMovies()
        test.assertValue(moviesExpected)
        test.assertValueCount(1)
        test.assertValue { it.size == 1 }
        test.assertNoErrors()
        test.assertComplete()
    }

    @Test
    fun repositoryGetFailure() {
        whenever(mockRepository.getMovies()).thenReturn(Single.error(throwable))

        val test = usecase.get().test()

        verify(mockRepository).getMovies()
        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }
}