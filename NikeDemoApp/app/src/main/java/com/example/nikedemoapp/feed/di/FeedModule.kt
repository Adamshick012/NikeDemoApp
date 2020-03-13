package com.example.nikedemoapp.feed.di

import androidx.lifecycle.ViewModel
import com.example.nikedemoapp.di.ViewModelKey
import com.example.nikedemoapp.feed.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FeedModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindViewModel(viewModel: FeedViewModel): ViewModel
}