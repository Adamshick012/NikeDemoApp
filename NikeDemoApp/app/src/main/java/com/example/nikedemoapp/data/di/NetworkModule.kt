package com.example.nikedemoapp.data.di

import com.example.nikedemoapp.data.RSSJsonApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    internal fun provideRSSJsonApi(retrofit: Retrofit): RSSJsonApi {
        return retrofit.create(RSSJsonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logging   = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client : OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://rss.itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}