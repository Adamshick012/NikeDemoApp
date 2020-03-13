package com.example.nikedemoapp.detail.di

import androidx.lifecycle.ViewModel
import com.example.nikedemoapp.detail.AlbumDetailViewModel
import com.example.nikedemoapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AlbumDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailViewModel::class)
    abstract fun bindViewModel(viewModel: AlbumDetailViewModel): ViewModel
}