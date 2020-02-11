package com.example.nikedemoapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeedData(
    @SerializedName("feed")
    @Expose
    val feed: Feed
) {
    data class Feed(
        @SerializedName("results")
        @Expose
        val results: List<Result>
    )
}