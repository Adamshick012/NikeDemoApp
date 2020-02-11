package com.example.nikedemoapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikedemoapp.repo.FeedSource
import com.example.nikedemoapp.models.Result
import kotlinx.coroutines.launch

class FeedViewModel  : ViewModel() {
    var topAlbumsList = MutableLiveData<MutableList<Result>>()
    private val repository: FeedSource = FeedSource()

    fun getMusicList(feedType: String) {

        viewModelScope.launch {
            try {
                val results = repository.getMusicList(feedType).body()?.feed?.results?.toMutableList()
                topAlbumsList.postValue(results)

            }catch (exception : Exception){
                Log.e("Failed to load albums list",""+exception.message)
            }


        }
    }
}