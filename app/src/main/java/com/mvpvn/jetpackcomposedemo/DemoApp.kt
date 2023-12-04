package com.mvpvn.jetpackcomposedemo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mvpvn.jetpackcomposedemo.di.DI

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DI.initialize(this)
        AndroidThreeTen.init(this)
    }
}