package com.example.nikedemoapp.feed

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nikedemoapp.models.Result

@BindingAdapter("albumName")
fun TextView.setResultImage(item: Result?) {
    item?.let {
        text = "${it.name}\nBY: ${it.artistName}"
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?)
{
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(ColorDrawable(Color.BLACK))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}