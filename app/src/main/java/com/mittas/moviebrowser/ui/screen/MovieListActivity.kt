package com.mittas.moviebrowser.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mittas.moviebrowser.R
import com.mittas.moviebrowser.di.DIHelper
import com.mittas.moviebrowser.domain.entity.movies.Movie
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DIHelper.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupUI()
        setupViewModel()
    }

    private fun setupUI() {

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            onMoviesUpdated(it)
        })
    }

    private fun onMoviesUpdated(movies: List<Movie>) {

    }


}
