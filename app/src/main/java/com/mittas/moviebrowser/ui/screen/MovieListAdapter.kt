package com.mittas.moviebrowser.ui.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mittas.moviebrowser.R
import com.mittas.moviebrowser.domain.entity.movies.Movie

class MovieListAdapter(private val clickAction: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(root, clickAction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View, private val clickAction: (Movie) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        // todo find item vies


        fun bind(movie: Movie) {
            itemView.setOnClickListener { clickAction(movie) }
        }
    }
}