package com.mittas.moviebrowser.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mittas.moviebrowser.R
import com.mittas.moviebrowser.di.DIHelper
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.ui.utils.setDivider
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject


class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieListViewModel

    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DIHelper.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setupUI()
        setupViewModel()
    }

    private fun setupUI() {
        adapter = MovieListAdapter(this) { onMovieClicked(it) }

        moviesRecyclerView.adapter = adapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.setDivider(R.drawable.divider)
    }

    private fun onMovieClicked(movie: Movie) {
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            onMoviesUpdated(it)
        })
    }

    private fun onMoviesUpdated(movies: List<Movie>) {
        adapter.setMovies(movies)
    }

}
