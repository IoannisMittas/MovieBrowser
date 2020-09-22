package com.mittas.moviebrowser.ui.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mittas.moviebrowser.data.network.ImageLoader

fun ImageView.loadImage(url: String) = ImageLoader.loadImage(this, url)

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val decoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
    ContextCompat.getDrawable(this.context, drawableRes)?.let {
        decoration.setDrawable(it)
        addItemDecoration(decoration)
    }
}