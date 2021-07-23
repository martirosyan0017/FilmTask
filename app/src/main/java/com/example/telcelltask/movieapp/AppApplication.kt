package com.example.telcelltask.movieapp

import android.app.Application

open class AppApplication : Application() {

    companion object {
        var appContext: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}