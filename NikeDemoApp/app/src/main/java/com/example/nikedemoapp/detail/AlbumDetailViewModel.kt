package com.example.nikedemoapp.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nikedemoapp.MyApplication
import com.example.nikedemoapp.R
import com.example.nikedemoapp.models.Result

class AlbumDetailViewModel (result: Result, app: Application) : AndroidViewModel(app) {
    private val _albumInfo = MutableLiveData<Result>()

    val albumInfo: LiveData<Result>
        get() = _albumInfo

    init {
        _albumInfo.value = result
    }

    val mainText = Transformations.map(albumInfo) {
        app.applicationContext.getString(
            R.string.ablum_info,
            it.artistName,
            it.name,
            it.genres.joinToString(separator = ", ") {genre -> genre.name },
            it.releaseDate,
            it.copyright
        )
    }
}