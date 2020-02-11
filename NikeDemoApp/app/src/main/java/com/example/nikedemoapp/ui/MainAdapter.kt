package com.example.nikedemoapp.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nikedemoapp.R
import com.example.nikedemoapp.models.Result
import kotlinx.android.synthetic.main.recyclerview_feed_item.view.*

class MainAdapter(onImageListener: OnImageListener) :
    ListAdapter<Result, MainAdapter.MyViewHolder>(diffCallback) {

    private lateinit var context : Context
    private var mOnImageListener: OnImageListener = onImageListener

    class MyViewHolder(mainView: View, onImageListener: OnImageListener) :
        RecyclerView.ViewHolder(mainView), View.OnClickListener {

        var onImageListener : OnImageListener
        init {
            this.onImageListener = onImageListener
            mainView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onImageListener.onImageClick(adapterPosition)
        }
    }

    interface OnImageListener{
        fun onImageClick(position: Int)
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.artistId == newItem.artistId

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mainView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_feed_item, parent, false)
        context = parent.context
        return MyViewHolder(mainView,mOnImageListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)
        holder.itemView.text_artist.text = "${post?.name}\nBY: ${post?.artistName}"
        Glide.with(context)
            .load(post?.artworkUrl100)
            .placeholder(ColorDrawable(Color.BLACK))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.itemView.imageViewItem)

    }
}