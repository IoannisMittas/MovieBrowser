package com.mittas.moviebrowser.ui.utils

import android.widget.ImageView
import com.mittas.moviebrowser.data.network.ImageLoader

fun ImageView.loadImage(url: String) = ImageLoader.loadImage(this, url)