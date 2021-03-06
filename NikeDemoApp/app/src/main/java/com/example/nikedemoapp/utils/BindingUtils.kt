package com.example.nikedemoapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.nikedemoapp.R
import com.example.nikedemoapp.feed.MainAdapter
import com.example.nikedemoapp.feed.iTuneApiStatus
import com.example.nikedemoapp.models.Album

@BindingAdapter("albumName")
fun TextView.setResultImage(item: Album?) {
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
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_launcher_background))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Album>?) {
    val adapter = recyclerView.adapter as MainAdapter
    adapter.submitList(data)
}

@BindingAdapter("feedApiStatus")
fun bindStatus(statusImageView: ImageView, status: iTuneApiStatus) {
    when(status) {
        iTuneApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        iTuneApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_error_black_24dp)
        }
        iTuneApiStatus.DONE ->
            statusImageView.visibility = View.GONE

    }
}