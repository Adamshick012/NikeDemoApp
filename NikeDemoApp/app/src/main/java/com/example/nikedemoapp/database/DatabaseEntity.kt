package com.example.nikedemoapp.database


import androidx.annotation.NonNull
import androidx.room.*
import com.example.nikedemoapp.models.Album
import com.example.nikedemoapp.models.Genre
import com.example.nikedemoapp.models.MusicFeed

@Entity(tableName = "album_table")
data class DatabaseAlbum constructor(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "albumId", index = true)
    val albumId: String,
    val artistId: String,
    val artistName: String,
    val artistUrl: String,
    val imageUrl: String,
    val copyrightText: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
)

fun List<DatabaseAlbumWithGenres>.asAlbumDomainModel() : List<Album> {
    return map {
        Album(
            albumId = it.album.albumId,
            artistName = it.album.artistName,
            artistUrl = it.album.artistUrl,
            imageUrl = it.album.imageUrl,
            copyrightText = it.album.copyrightText,
            kind = it.album.kind,
            name = it.album.name,
            releaseDate = it.album.releaseDate,
            url = it.album.url
        )
    }
}

@Entity(tableName = "genre_table")
data class DatabaseGenre(
    @PrimaryKey
    @NonNull
    val genreId: String,
    val name: String
)

fun List<DatabaseGenre>.asGenreDomainModel() : List<Genre> {
    return map {
        Genre(
            genreId = it.genreId,
            name = it.name
        )
    }
}

@Entity(primaryKeys = ["albumId", "genreId"])
data class AlbumGenreCrossRef(
    val albumId: String,
    @ColumnInfo(name = "genreId", index = true)
    val genreId: String
)

data class DatabaseAlbumWithGenres(
    @Embedded val album: DatabaseAlbum,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "genreId",
        associateBy = Junction(AlbumGenreCrossRef::class)
    )
    val genres: List<DatabaseGenre>
)


@Entity(tableName = "feed_table")
data class DatabaseMusicFeed(
    @PrimaryKey
    @NonNull
    val feedId: String,
    val title: String
)

fun DatabaseMusicFeedWithAlbums.asMusicFeedDomainModel() : MusicFeed {
    return MusicFeed(
            title = feed.title,
            albums = albums.map {album -> Album(
                albumId = album.albumId,
                artistName = album.artistName,
                artistUrl = album.artistUrl,
                imageUrl = album.imageUrl,
                copyrightText = album.copyrightText,
                kind = album.kind,
                name = album.name,
                releaseDate = album.releaseDate,
                url = album.url
            ) }
        )
}

@Entity(primaryKeys = ["feedId", "albumId"])
data class MusicFeedAlbumCrossRef(
    val feedId: String,
    val albumId: String
)

data class DatabaseMusicFeedWithAlbums(
    @Embedded val feed: DatabaseMusicFeed,
    @Relation(
        entity = DatabaseAlbum::class,
        parentColumn = "feedId",
        entityColumn = "albumId",
        associateBy = Junction(
            value =MusicFeedAlbumCrossRef::class,
            parentColumn = "feedId",
            entityColumn = "albumId")
    )
    val albums: List<DatabaseAlbum>
)
