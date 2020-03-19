package com.example.nikedemoapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Album(
    val albumId: String,
    val artistName: String,
    val artistUrl: String,
    val imageUrl: String,
    val copyrightText: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
)
data class Genre(
    val genreId: String,
    val name: String
)