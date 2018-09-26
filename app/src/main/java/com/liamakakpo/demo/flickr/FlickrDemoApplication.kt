package com.liamakakpo.demo.flickr

import android.app.Application
import org.koin.android.ext.android.startKoin

class FlickrDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(viewModule, schedulerModule, flickrModule))
    }
}