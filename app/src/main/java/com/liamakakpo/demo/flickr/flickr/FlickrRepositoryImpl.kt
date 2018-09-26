package com.liamakakpo.demo.flickr.flickr

import com.liamakakpo.demo.flickr.api.FlickrApi
import com.liamakakpo.demo.flickr.model.FlickrImage
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FlickrRepositoryImpl : FlickrRepository {

    override fun getFeed(): Observable<List<FlickrImage>> {
        return FlickrApi.getService().getFeed()
                .map { it.feed }
                .subscribeOn(Schedulers.io())
    }
}