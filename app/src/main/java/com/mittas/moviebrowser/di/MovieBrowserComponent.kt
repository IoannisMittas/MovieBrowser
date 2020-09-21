package com.mittas.moviebrowser.di

import ApiModule
import com.mittas.moviebrowser.ui.screen.MovieListActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulersModule::class, ViewModelsModule::class])
interface MovieBrowserComponent {

    fun inject(activity: MovieListActivity)
}