package com.example.nikedemoapp.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nikedemoapp.MyApplication
import com.example.nikedemoapp.models.Result

class AlbumDetailViewModelFactory (
    private val result: Result,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
            return AlbumDetailViewModel(result, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}