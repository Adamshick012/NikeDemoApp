package com.example.nikedemoapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album_table")
    fun getAlbums(): LiveData<List<DatabaseAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeedWithAlbums(feed: DatabaseMusicFeed, vararg albums: DatabaseAlbum)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbumsWithGenres(album: DatabaseAlbum, genres: List<DatabaseGenre>)

    @Transaction
    @Query("SELECT * FROM feed_table")
    fun getFeedWithAlbums(): LiveData<List<DatabaseMusicFeedWithAlbums>>

    @Transaction
    @Query("SELECT * FROM album_table")
    fun getAlbumWithGenres(): LiveData<List<DatabaseAlbumWithGenres>>
}

@Database(entities = [
        DatabaseAlbum::class,
        DatabaseGenre::class,
        DatabaseMusicFeed::class,
        AlbumGenreCrossRef::class,
        MusicFeedAlbumCrossRef::class
    ],
    version = 1)
abstract class MainDatabase: RoomDatabase() {
    abstract val albumDao: AlbumDao
}

//private lateinit var INSTANCE: MainDatabase

//fun getDatabase(context: Context): MainDatabase {
//    synchronized(MainDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                MainDatabase::class.java,
//                "albums"
//            ).build()
//        }
//    }
//    return INSTANCE
//}