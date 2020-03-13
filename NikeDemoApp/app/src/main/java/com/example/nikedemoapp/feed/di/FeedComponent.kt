package com.example.nikedemoapp.feed.di

import com.example.nikedemoapp.feed.FeedFragment
import dagger.Subcomponent

@Subcomponent(modules = [FeedModule::class])
interface FeedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FeedComponent
    }

    fun inject(fragment: FeedFragment)
}