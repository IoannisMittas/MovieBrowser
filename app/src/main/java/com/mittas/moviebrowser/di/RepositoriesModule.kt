package com.mittas.moviebrowser.di

import com.mittas.moviebrowser.data.repositories.movies.MoviesRepositoryImpl
import com.mittas.moviebrowser.domain.repository.movies.MoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(repoImpl: MoviesRepositoryImpl): MoviesRepository
}