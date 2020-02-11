package com.example.nikedemoapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Result(
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
        @SerializedName("name")
        @Expose
        val name: String
    )
}