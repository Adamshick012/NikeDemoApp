package com.example.nikedemoapp.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nikedemoapp.databinding.FeedItemBinding
import com.example.nikedemoapp.models.Album

class MainAdapter(private val onImageListener: ImageListener) :
    ListAdapter<Album, MainAdapter.MyViewHolder>(
        diffCallback
    ) {

    private lateinit var context : Context

    class MyViewHolder(val binding: FeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(onImageListener: ImageListener, item: Album) {
            binding.result = item
            binding.clickListener = onImageListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FeedItemBinding.inflate(layoutInflater, parent, false)

                return MyViewHolder(
                    binding
                )
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(onImageListener, item)
    }
}

class ImageListener(val clickListener: (albumId: Album) -> Unit) {
    fun onClick(album: Album) = clickListener(album)
}