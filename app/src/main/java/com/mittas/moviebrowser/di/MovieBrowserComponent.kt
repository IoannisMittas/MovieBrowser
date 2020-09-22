package com.mittas.moviebrowser.di

import com.mittas.moviebrowser.ui.screen.movies.MovieListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulersModule::class, RepositoriesModule::class, ApiModule::class, ViewModelsModule::class])
interface MovieBrowserComponent {

    fun inject(activity: MovieListActivity)
}