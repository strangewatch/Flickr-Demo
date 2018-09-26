package com.liamakakpo.demo.flickr.ui.feed

import com.liamakakpo.demo.flickr.model.FlickrImage
import com.liamakakpo.demo.flickr.ui.BaseView

object ImageFeedContract {

    interface View : BaseView {
        fun displayImages(albums: List<FlickrImage>)

        fun onEmptyFeed()

        fun onError()
    }

    interface Presenter {
        fun getLatestImages()
    }
}