package com.example.nikedemoapp

import android.app.Application
import com.example.nikedemoapp.di.AppComponent
import com.example.nikedemoapp.di.DaggerAppComponent

open class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}