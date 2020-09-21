package com.mittas.moviebrowser.data.network

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {

    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView).load(url).into(imageView)
    }
}