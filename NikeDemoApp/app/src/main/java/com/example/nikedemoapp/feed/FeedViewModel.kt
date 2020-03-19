package com.example.nikedemoapp.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikedemoapp.repo.FeedRepository
import com.example.nikedemoapp.models.Album
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

enum class iTuneApiStatus { LOADING, ERROR, DONE }

class FeedViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {

    private val _topAlbumsList = repository.albums
    val topAlbumsList : LiveData<List<Album>>
        get() = _topAlbumsList

    private val _navigateToAlbumDetail = MutableLiveData<String>()
    val navigateToAlbumDetail
        get() = _navigateToAlbumDetail

    private val _status = MutableLiveData<iTuneApiStatus>()

    val status
        get() = _status

    fun onAlbumClicked(item: String) {
        _navigateToAlbumDetail.value = item
    }

    fun onAlbumDetailNavigated() {
        _navigateToAlbumDetail.value = null
    }

    init {
        getMusicList("top-albums")
    }

    fun getMusicList(feedType: String) {
        _status.value = iTuneApiStatus.LOADING
        runBlocking {
            try {
                repository.getMusicList(feedType)
                _status.value = iTuneApiStatus.DONE
            }catch (exception : Throwable){
                _status.value = iTuneApiStatus.ERROR
                Log.e("Failed to load albums list","${exception.message}")
            }


        }
    }
}