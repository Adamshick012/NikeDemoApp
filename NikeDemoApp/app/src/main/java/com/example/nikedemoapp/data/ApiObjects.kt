package com.example.nikedemoapp.data

import com.example.nikedemoapp.database.DatabaseAlbum
import com.example.nikedemoapp.database.DatabaseMusicFeed
import com.example.nikedemoapp.database.MusicFeedAlbumCrossRef
import com.example.nikedemoapp.models.Album
import com.example.nikedemoapp.models.MusicFeed
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeedData(
    @SerializedName("feed")
    @Expose
    val feed: Feed
) {
    data class Feed(
        @SerializedName("id")
        @Expose
        val feedId: String,
        @SerializedName("title")
        @Expose
        val title: String,
        @SerializedName("results")
        @Expose
        val results: List<Result>
    )
    {
        data class Result(
            @SerializedName("id")
            @Expose
            val id: String,
            @SerializedName("artistId")
            @Expose
            val artistId: String,
            @SerializedName("artistName")
            @Expose
            val artistName: String,
            @SerializedName("artistUrl")
            @Expose
            val artistUrl: String,
            @SerializedName("artworkUrl100")
            @Expose
            val artworkUrl100: String,
            @SerializedName("copyright")
            @Expose
            val copyright: String,
            @SerializedName("genres")
            @Expose
            val genres: List<Genre>,
            @SerializedName("kind")
            @Expose
            val kind: String,
            @SerializedName("name")
            @Expose
            val name: String,
            @SerializedName("releaseDate")
            @Expose
            val releaseDate: String,
            @SerializedName("url")
            @Expose
            val url: String
        ) {

            data class Genre(
                @SerializedName("genreId")
                @Expose
                val genreId: String,
                @SerializedName("name")
                @Expose
                val name: String
            )
        }
    }
}

//fun FeedData.asMusicFeedDomainModel(): MusicFeed {
//    return MusicFeed(
//        title = feed.title,
//        albums = feed.results.map { result ->
//            Album(
//                artistName = result.artistName,
//                artistUrl = result.artistUrl,
//                imageUrl = result.artworkUrl100,
//                copyrightText = result.copyright,
//                genres = result.genres.map { Album.Genre (
//                    name = it.name
//                ) },
//                kind = result.kind,
//                name = result.name,
//                releaseDate = result.releaseDate,
//                url = result.url
//            )
//        }
//    )
//}

fun FeedData.asMusicFeedDatabaseModel(): DatabaseMusicFeed {
    return DatabaseMusicFeed(
        feedId = feed.feedId,
        title = feed.title
    )
}

fun FeedData.asAlbumDatabaseModel(): List<DatabaseAlbum> {
    return feed.results.map { result ->
        DatabaseAlbum(
            albumId = result.id,
            artistId = result.artistId,
            artistName = result.artistName,
            artistUrl = result.artistUrl,
            imageUrl = result.artworkUrl100,
            copyrightText = result.copyright,
            kind = result.kind,
            name = result.name,
            releaseDate = result.releaseDate,
            url = result.url)
    }
}

fun FeedData.asAlbumFeedCrossRefDatabaseModel(): List<MusicFeedAlbumCrossRef> {
    return feed.results.map { result ->
        MusicFeedAlbumCrossRef(
            albumId = result.id,
            feedId = feed.feedId)
    }
}
