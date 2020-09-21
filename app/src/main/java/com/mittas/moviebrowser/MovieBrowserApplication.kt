package com.mittas.moviebrowser

import android.app.Application
import com.mittas.moviebrowser.di.DIHelper
import com.mittas.moviebrowser.di.DaggerMovieBrowserComponent
import timber.log.Timber

class MovieBrowserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
    }

    private fun initDagger() {
        DIHelper.appComponent = DaggerMovieBrowserComponent.create()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}