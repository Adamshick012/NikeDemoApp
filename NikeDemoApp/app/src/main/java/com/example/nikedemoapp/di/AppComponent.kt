package com.example.nikedemoapp.di

import android.content.Context
import com.example.nikedemoapp.data.di.NetworkModule
import com.example.nikedemoapp.detail.di.AlbumDetailComponent
import com.example.nikedemoapp.feed.di.FeedComponent
import com.example.nikedemoapp.repo.FeedRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun addAlbumDetailComponent(): AlbumDetailComponent.Factory
    fun addFeedComponent(): FeedComponent.Factory
//    fun inject(fragment: FeedFragment)
    val feedRepository: FeedRepository

}

@Module(
    subcomponents = [
        AlbumDetailComponent::class,
        FeedComponent::class
    ]
)
object SubcomponentsModule