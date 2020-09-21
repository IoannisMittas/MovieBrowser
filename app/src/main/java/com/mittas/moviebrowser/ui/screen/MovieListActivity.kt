package com.mittas.moviebrowser.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mittas.moviebrowser.R
import com.mittas.moviebrowser.di.DIHelper

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        DIHelper.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
    }
}
