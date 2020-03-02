package com.example.nikedemoapp.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.nikedemoapp.models.Result

@BindingAdapter("albumName")
fun TextView.setResultImage(item: Result?) {
    item?.let {
        text = "${it.name}\nBY: ${it.artistName}"
    }
}