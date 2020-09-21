package com.mittas.moviebrowser.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Named

@Module
class SchedulersModule {

    @Provides
    @Named("postScheduler")
    fun providePostScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Named("workScheduler")
    fun provideWorkScheduler(): Scheduler {
        return Schedulers.io()
    }
}