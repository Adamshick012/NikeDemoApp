package com.example.nikedemoapp.detail.di

import com.example.nikedemoapp.detail.AlbumDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [AlbumDetailModule::class])
interface AlbumDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AlbumDetailComponent
    }

    fun inject(fragment: AlbumDetailFragment)

}