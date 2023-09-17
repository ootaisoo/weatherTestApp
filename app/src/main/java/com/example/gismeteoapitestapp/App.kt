package com.example.gismeteoapitestapp

import android.app.Application
import android.content.Context
import com.example.gismeteoapitestapp.di.DaggerMainComponent
import com.example.gismeteoapitestapp.di.MainComponent

class App : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.create()
    }
}

val Context.component: MainComponent
    get() = when (this) {
        is App -> mainComponent
        else -> (this.applicationContext as App).mainComponent
    }