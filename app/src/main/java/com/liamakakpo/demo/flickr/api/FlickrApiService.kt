package com.liamakakpo.demo.flickr.api

import com.liamakakpo.demo.flickr.model.FlickrFeed
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface FlickrApiService {

    companion object {
        const val BASE_URL = "https://api.flickr.com/"
    }

    @GET("/services/feeds/photos_public.gne?&lang=en-us&format=json&nojsoncallback=1")
    fun getFeed(): Observable<FlickrFeed>
}