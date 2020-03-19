package com.example.nikedemoapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nikedemoapp.R
import com.example.nikedemoapp.data.RSSJsonApi
import com.example.nikedemoapp.data.asAlbumDatabaseModel
import com.example.nikedemoapp.data.asAlbumFeedCrossRefDatabaseModel
import com.example.nikedemoapp.data.asMusicFeedDatabaseModel
import com.example.nikedemoapp.database.MainDatabase
import com.example.nikedemoapp.database.asMusicFeedDomainModel
import com.example.nikedemoapp.models.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultFeedRepository @Inject constructor(private val databaseMain: MainDatabase)
    : FeedRepository{
    @Inject
    lateinit var rssFeedApi: RSSJsonApi
    var feedTitle = "Top Albums"
    override val albums: LiveData<List<Album>> = Transformations.map(databaseMain.albumDao.getFeedWithAlbums(feedTitle)) {
        it.asMusicFeedDomainModel().albums
    }

    override suspend fun getMusicList(feedType: String) {
        withContext(Dispatchers.IO) {
            val feedData = rssFeedApi.getFeed(feedType).body()
            feedData?.let {
                databaseMain.albumDao.insertFeedWithAlbums(
                    feedData.asMusicFeedDatabaseModel(),
                    feedData.asAlbumDatabaseModel(),
                    feedData.asAlbumFeedCrossRefDatabaseModel()
                )
            }
        }
    }
}