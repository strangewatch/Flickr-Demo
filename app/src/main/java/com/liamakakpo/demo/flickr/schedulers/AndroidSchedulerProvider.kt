package com.liamakakpo.demo.flickr.schedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulerProvider : SchedulerProvider {

    override fun ui() = AndroidSchedulers.mainThread()

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io()
}