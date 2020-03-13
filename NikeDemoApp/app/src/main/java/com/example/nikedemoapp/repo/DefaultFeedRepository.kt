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

class DefaultFeedRepository @Inject constructor(private val databaseMain: MainDatabase)
    : FeedRepository{
    @Inject
    lateinit var rssFeedApi: RSSJsonApi

    override val albums: LiveData<List<Album>> = Transformations.map(databaseMain.albumDao.getAlbumWithGenres()) {
        it.asAlbumDomainModel()
    }

    override suspend  fun getMusicList(feedType: String) {
        val feedData = rssFeedApi.getFeed(feedType).body()
        feedData?.let {
            databaseMain.albumDao.insertFeedWithAlbums(
                feedData.asMusicFeedDatabaseModel(),
                *feedData.asAlbumDatabaseModel())
        }
    }
}