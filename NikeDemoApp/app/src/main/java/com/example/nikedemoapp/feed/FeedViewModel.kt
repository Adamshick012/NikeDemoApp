package com.example.nikedemoapp.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikedemoapp.repo.FeedSource
import com.example.nikedemoapp.models.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class iTuneApiStatus { LOADING, ERROR, DONE }

class FeedViewModel @Inject constructor(private val repository: FeedSource) : ViewModel() {

    private val _topAlbumsList = MutableLiveData<MutableList<Result>>()
    val topAlbumsList : LiveData<MutableList<Result>>
        get() = _topAlbumsList


    private val _navigateToAlbumDetail = MutableLiveData<Result>()
    val navigateToAlbumDetail
        get() = _navigateToAlbumDetail

    private val _status = MutableLiveData<iTuneApiStatus>()

    val status
        get() = _status

    fun onAlbumClicked(item: Result) {
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
        viewModelScope.launch {
            try {

                val results = repository.getMusicList(feedType).body()?.feed?.results?.toMutableList()
                _status.value = iTuneApiStatus.DONE
                _topAlbumsList.postValue(results)

            }catch (exception : Throwable){
                _status.value = iTuneApiStatus.ERROR
                Log.e("Failed to load albums list","${exception.message}")
                _topAlbumsList.value = ArrayList()
            }


        }
    }
}