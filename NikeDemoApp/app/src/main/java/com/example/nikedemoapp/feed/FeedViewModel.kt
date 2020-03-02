package com.example.nikedemoapp.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikedemoapp.repo.FeedSource
import com.example.nikedemoapp.models.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val repository: FeedSource) : ViewModel() {
    var topAlbumsList = MutableLiveData<MutableList<Result>>()

    private val _navigateToAlbumDetail = MutableLiveData<Result>()
    val navigateToAlbumDetail
        get() = _navigateToAlbumDetail

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

        viewModelScope.launch {
            try {
                val results = repository.getMusicList(feedType).body()?.feed?.results?.toMutableList()
                topAlbumsList.postValue(results)

            }catch (exception : Throwable){
                Log.e("Failed to load albums list","${exception.message}")
            }


        }
    }
}