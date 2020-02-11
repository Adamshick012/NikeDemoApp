package com.example.nikedemoapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RSSFeedService {

    var logging   = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
    private var client : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://rss.itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}