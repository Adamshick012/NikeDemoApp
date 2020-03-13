package com.example.nikedemoapp.data.di

import com.example.nikedemoapp.data.RSSJsonApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRSSJsonApi(retrofit: Retrofit): RSSJsonApi {
        return retrofit.create(RSSJsonApi::class.java)
    }

    @JvmStatic
    @Reusable
    @Provides
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