package com.example.nikedemoapp.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.nikedemoapp.R
import com.example.nikedemoapp.models.Album
import com.example.nikedemoapp.repo.FeedRepository
import javax.inject.Inject

class AlbumDetailViewModel @Inject constructor(private val feedRepo: FeedRepository) : ViewModel() {
    private val _albumInfo = MutableLiveData<Album>()

    val albumInfo: LiveData<Album>
        get() = _albumInfo

    private val _artistName = MutableLiveData<String>()

    val artistName: LiveData<String>
        get() = _artistName

    private val _itunesUrl = MutableLiveData<String>()

    val itunesUrl: LiveData<String>
        get() = _itunesUrl

    //GET ALBUM INFO VIA DATABASE CALL HERE


//    init {
//        _albumInfo.value = album
//    }


//    val mainText = Transformations.map(albumInfo) {
//        applicationContext.getString(
//            R.string.ablum_info,
//            it.artistName,
//            it.name,
//            it.genres.joinToString(separator = ", ") {genre -> genre.name },
//            it.releaseDate,
//            it.copyrightText
//        )
//    }
}