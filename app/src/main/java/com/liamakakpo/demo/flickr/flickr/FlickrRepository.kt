package com.liamakakpo.demo.flickr.flickr

import com.liamakakpo.demo.flickr.model.FlickrImage
import io.reactivex.Observable

interface FlickrRepository {

    fun getFeed(): Observable<List<FlickrImage>>

}