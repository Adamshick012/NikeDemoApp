package com.example.nikedemoapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nikedemoapp.data.RSSFeedService
import com.example.nikedemoapp.data.RSSJsonApi
import com.example.nikedemoapp.data.asAlbumDatabaseModel
import com.example.nikedemoapp.data.asMusicFeedDatabaseModel
import com.example.nikedemoapp.database.MainDatabase
import com.example.nikedemoapp.database.asAlbumDomainModel
import com.example.nikedemoapp.models.Album
import javax.inject.Inject

interface FeedRepository  {
//    private var rssFeedApi : RSSJsonApi = RSSFeedService.createService(RSSJsonApi::class.java)
    val albums: LiveData<List<Album>>
    suspend fun getMusicList(feedType: String)
}