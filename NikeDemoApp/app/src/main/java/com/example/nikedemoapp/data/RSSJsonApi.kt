package com.example.nikedemoapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RSSJsonApi  {
    @GET("api/v1/us/apple-music/{feedType}/all/50/explicit.json")
    suspend fun getFeed(@Path("feedType") feedType: String): Response<FeedData>
}