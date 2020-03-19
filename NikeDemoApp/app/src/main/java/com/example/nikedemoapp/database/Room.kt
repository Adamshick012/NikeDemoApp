package com.example.nikedemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album_table")
    fun getAlbums(): LiveData<List<DatabaseAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeedWithAlbums(feed: DatabaseMusicFeed, albums: List<DatabaseAlbum>, crossref: List<MusicFeedAlbumCrossRef>
    )
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbumsWithGenres(album: DatabaseAlbum, genres: List<DatabaseGenre>)

    @Transaction
    @Query("SELECT * FROM feed_table WHERE title = :title")
    fun getFeedWithAlbums(title: String): LiveData<DatabaseMusicFeedWithAlbums>

    @Transaction
    @Query("SELECT * FROM album_table WHERE albumId = :albumId")
    fun getAlbumWithGenres(albumId: String): LiveData<DatabaseAlbumWithGenres>
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