package com.example.nikedemoapp.di

import com.example.nikedemoapp.repo.DefaultFeedRepository
import com.example.nikedemoapp.repo.FeedRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultFeedRepository): FeedRepository
}