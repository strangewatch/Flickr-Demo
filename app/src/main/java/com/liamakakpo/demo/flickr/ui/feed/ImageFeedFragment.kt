package com.liamakakpo.demo.flickr.ui.feed

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.liamakakpo.demo.flickr.R
import com.liamakakpo.demo.flickr.model.FlickrImage
import com.liamakakpo.demo.flickr.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.get

class ImageFeedFragment : BaseMvpFragment<ImageFeedContract.View, ImageFeedPresenter>(),
        ImageFeedContract.View {

    override fun createPresenter(): ImageFeedPresenter = get()

    private lateinit var imageFeedListAdapter: ImageFeedListAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageFeedListAdapter = ImageFeedListAdapter(get(), ::showImageInfo)
        swipeRefreshLayout.setOnRefreshListener {
            errorDisplay.visibility = View.GONE

            presenter?.getLatestImages()
        }
        recyclerView.init()
    }

    private fun showImageInfo(flickrImage: FlickrImage) {

    }

    override fun onResume() {
        super.onResume()
        presenter?.getLatestImages()
    }

    override fun displayImages(images: List<FlickrImage>) {
        imageFeedListAdapter.update(images)

        recyclerView.visibility = View.VISIBLE
        errorDisplay.visibility = View.GONE

        swipeRefreshLayout.isRefreshing = false
    }

    override fun onError() {
        showWarning(R.string.feed_error)
    }

    override fun onEmptyFeed() {
        showWarning(R.string.feed_empty)
    }

    private fun showWarning(@StringRes stringResId: Int) {
        recyclerView.visibility = View.GONE
        errorDisplay.visibility = View.VISIBLE

        errorDisplay.setText(stringResId)

        swipeRefreshLayout.isRefreshing = false
    }

    private fun RecyclerView.init() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))

        layoutManager = linearLayoutManager
        adapter = imageFeedListAdapter
    }
}