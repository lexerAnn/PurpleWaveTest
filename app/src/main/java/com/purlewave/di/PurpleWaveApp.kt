package com.kola.basic.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PurpleWaveApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}