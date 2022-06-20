package com.example.todoremake

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(applicationContext)
    }
}