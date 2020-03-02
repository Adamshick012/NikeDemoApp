package com.example.nikedemoapp.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nikedemoapp.databinding.FeedItemBinding
import com.example.nikedemoapp.models.Result

class MainAdapter(private val onImageListener: ImageListener) :
    ListAdapter<Result, MainAdapter.MyViewHolder>(diffCallback) {

    private lateinit var context : Context

    class MyViewHolder(val binding: FeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(onImageListener: ImageListener, context: Context, item: Result) {
            binding.result = item
            binding.clickListener = onImageListener
            Glide.with(context)
                .load(item.artworkUrl100)
                .placeholder(ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageViewItem)
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FeedItemBinding.inflate(layoutInflater, parent, false)

                return MyViewHolder(binding)
            }
        }
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.artistId == newItem.artistId

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(onImageListener, context, item)
    }
}

class ImageListener(val clickListener: (albumId: Result) -> Unit) {
    fun onClick(album: Result) = clickListener(album)
}