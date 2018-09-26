package com.liamakakpo.demo.flickr

import com.liamakakpo.demo.flickr.flickr.FlickrRepository
import com.liamakakpo.demo.flickr.model.FlickrImage
import com.liamakakpo.demo.flickr.model.Media
import com.liamakakpo.demo.flickr.schedulers.SingleSchedulerProvider
import com.liamakakpo.demo.flickr.ui.feed.ImageFeedContract
import com.liamakakpo.demo.flickr.ui.feed.ImageFeedPresenter
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ImageFeedPresenterTest {

    private var presenter: ImageFeedPresenter

    @Mock
    private lateinit var view: ImageFeedContract.View

    @Mock
    private lateinit var repository: FlickrRepository

    private val schedulerProvider = SingleSchedulerProvider(
            Schedulers.trampoline()
    )

    private fun getMockedImages() = listOf(
            FlickrImage("A Day at the Races (Churchill Downs)",
                    "https://www.flickr.com/photos/rodneydunning/43120731170/?rb=1",
                    Media("https://farm2.staticflickr.com//1951//43120731170_9dff40a994_m.jpg"),
                    "2018-09-22T17:28:36-08:00",
                    "Description",
                    "2018-09-26T18:05:52Z",
                    "nobody@flickr.com",
                    "54688130@N02",
                    "churchilldowns horses horseracing kentucky louisville")
    )

    init {
        MockitoAnnotations.initMocks(this)
        presenter = ImageFeedPresenter(schedulerProvider, repository)
        presenter.attachView(view)
    }

    @Test
    fun displayFeed_WithResults() {

        val mockedImages = getMockedImages()

        doReturn(Observable.just(mockedImages)).whenever(repository).getFeed()

        presenter.getLatestImages()

        verify(view).displayImages(mockedImages)
    }

    @Test
    fun displayFeed_Empty() {

        val mockedImages = listOf<FlickrImage>()

        doReturn(Observable.just(mockedImages)).whenever(repository).getFeed()

        presenter.getLatestImages()

        verify(view).onEmptyFeed()
    }

    @Test
    fun displayFeed_Error() {

        doReturn(Observable.error<Exception>(Exception())).whenever(repository).getFeed()

        presenter.getLatestImages()

        verify(view).onError()
    }
}
