package com.liamakakpo.demo.flickr.ui.feed

import com.liamakakpo.demo.flickr.flickr.FlickrRepository
import com.liamakakpo.demo.flickr.model.FlickrImage
import com.liamakakpo.demo.flickr.schedulers.SchedulerProvider
import com.liamakakpo.demo.flickr.ui.BasePresenter
import io.reactivex.disposables.CompositeDisposable

class ImageFeedPresenter(private val schedulerProvider: SchedulerProvider,
                         private val flickrRepository: FlickrRepository) : BasePresenter<ImageFeedContract.View>(), ImageFeedContract.Presenter {

    private var feedDisposable = CompositeDisposable()

    override fun getLatestImages() {
        flickrRepository.getFeed()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::onNextResult, ::onError)
    }

    override fun detachView() {
        feedDisposable?.dispose()
        super.detachView()
    }

    private fun onNextResult(images: List<FlickrImage>) {
        if (images.isNotEmpty()) {
            view?.displayImages(images)
        } else {
            view?.onEmptyFeed()
        }
    }

    private fun onError(throwable: Throwable) {
        view?.onError()
    }
}