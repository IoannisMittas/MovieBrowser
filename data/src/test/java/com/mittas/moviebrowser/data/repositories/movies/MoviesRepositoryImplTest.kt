package com.mittas.moviebrowser.data.repositories.movies

import com.mittas.moviebrowser.data.network.movies.MovieDataResponse
import com.mittas.moviebrowser.data.network.movies.MovieOffersResponse
import com.mittas.moviebrowser.data.network.movies.MoviesRemoteApi
import com.mittas.moviebrowser.data.repositories.movieData1
import com.mittas.moviebrowser.data.repositories.movieData2
import com.mittas.moviebrowser.data.repositories.movieOffer1
import com.mittas.moviebrowser.data.repositories.movieOffer2
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    private lateinit var repository: MoviesRepositoryImpl

    private val mockMoviesRemoteApi: MoviesRemoteApi = mock()

    private val movieDataList: List<MovieDataResponse.MovieData> = listOf(movieData1, movieData2)
    private val movieOffersResponse = MovieOffersResponse("www.example.com", listOf(movieOffer1, movieOffer2))

    private val moviesExpected: List<Movie> = listOf(
        Movie(
            id = movieOffer1.movieId!!,
            price = movieOffer1.price!!,
            imageUrl = movieOffersResponse.imageBaseUrl + movieOffer1.imageUrlPath,
            isAvailable = movieOffer1.isAvailable
        ).apply {
            title = movieData1.title!!
            subTitle = movieData1.subtitle!!
        },
        Movie(
            id = movieOffer2.movieId!!,
            price = movieOffer2.price!!,
            imageUrl = movieOffersResponse.imageBaseUrl + movieOffer2.imageUrlPath,
            isAvailable = movieOffer2.isAvailable
        ).apply {
            title = movieData1.title!!
            subTitle = movieData2.subtitle!!
        }
    )

    private val throwable = Throwable()

    @Before
    fun setup() {
        repository = MoviesRepositoryImpl(mockMoviesRemoteApi)
    }

    @Test
    fun getMoviesSuccess() {
        whenever(mockMoviesRemoteApi.getMovieOffers()).thenReturn(Single.just(movieOffersResponse))
        whenever(mockMoviesRemoteApi.getMovieData()).thenReturn(Single.just(MovieDataResponse(movieDataList)))

        val test = repository.getMovies().test()

        verify(mockMoviesRemoteApi).getMovieOffers()
        verify(mockMoviesRemoteApi).getMovieData()
        test.assertNoErrors()
        test.assertComplete()
        test.assertValue(moviesExpected)
        test.assertValue { it.size == 2 }
    }

    @Test
    fun getMoviesError() {
        whenever(mockMoviesRemoteApi.getMovieOffers()).thenReturn(Single.error(throwable))

        val test = repository.getMovies().test()

        verify(mockMoviesRemoteApi).getMovieOffers()
        test.assertError(throwable)
    }
}