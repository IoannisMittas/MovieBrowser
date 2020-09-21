package com.mittas.moviebrowser.di

import ApiModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulersModule::class, RepositoriesModule::class, ApiModule::class, ViewModelsModule::class])
interface MovieBrowserComponent {
    //todo injections here
}