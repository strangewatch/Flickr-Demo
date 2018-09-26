package com.liamakakpo.demo.flickr.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liamakakpo.demo.flickr.R
import com.liamakakpo.demo.flickr.ui.feed.ImageFeedFragment
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private val imageFeedFragment: ImageFeedFragment = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(
                            R.id.container,
                            imageFeedFragment,
                            imageFeedFragment::class.java.name
                    ).commit()
        }
    }
}