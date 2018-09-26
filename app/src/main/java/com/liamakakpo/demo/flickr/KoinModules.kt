package com.liamakakpo.demo.flickr

import com.liamakakpo.demo.flickr.flickr.FlickrRepository
import com.liamakakpo.demo.flickr.flickr.FlickrRepositoryImpl
import com.liamakakpo.demo.flickr.schedulers.AndroidSchedulerProvider
import com.liamakakpo.demo.flickr.schedulers.SchedulerProvider
import com.liamakakpo.demo.flickr.ui.feed.ImageFeedFragment
import com.liamakakpo.demo.flickr.ui.feed.ImageFeedPresenter
import org.koin.dsl.module.module

val schedulerModule = module {
    single<SchedulerProvider> { AndroidSchedulerProvider() }
}

val viewModule = module {
    factory { ImageFeedFragment() }
    factory { ImageFeedPresenter(get(), get()) }
}

val flickrModule = module {
    single<FlickrRepository> { FlickrRepositoryImpl() }
}
