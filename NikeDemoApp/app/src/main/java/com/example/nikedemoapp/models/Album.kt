package com.example.nikedemoapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val artistName: String,
    val artistUrl: String,
    val imageUrl: String,
    val copyrightText: String,
    val genres: List<Genre>,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
): Parcelable {
    @Parcelize
    data class Genre(
        val name: String
    ): Parcelable
}