package com.example.nikedemoapp.di

import com.example.nikedemoapp.feed.FeedFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {
    fun inject(fragment: FeedFragment)
}