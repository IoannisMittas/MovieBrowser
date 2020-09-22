package com.mittas.moviebrowser.ui.screen.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mittas.moviebrowser.R
import com.mittas.moviebrowser.domain.entity.movies.Movie
import com.mittas.moviebrowser.ui.utils.loadImage

class MovieListAdapter(private val context: Context, private val clickAction: (Movie) -> Unit) :
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

        private var imageView: ImageView = itemView.findViewById(R.id.imageView)
        private var priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private var availableTextView: TextView = itemView.findViewById(R.id.availableTextView)
        private var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private var subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)

        fun bind(movie: Movie) {
            movie.imageUrl?.let { imageView.loadImage(it) }

            priceTextView.text = movie.price

            if(movie.isAvailable) setAvailableTextView() else setNotAvailableTextView()

            titleTextView.text = movie.title
            subtitleTextView.text = movie.subTitle

            itemView.setOnClickListener { clickAction(movie) }
        }

        private fun setAvailableTextView() {
            availableTextView.text =  context.getString(R.string.available_movie)
            availableTextView.setTextColor(context.resources.getColor(R.color.text_green, null))
        }

        private fun setNotAvailableTextView() {
            availableTextView.text =  context.getString(R.string.not_available_movie)
            availableTextView.setTextColor(context.resources.getColor(R.color.text_red, null))
        }
    }
}