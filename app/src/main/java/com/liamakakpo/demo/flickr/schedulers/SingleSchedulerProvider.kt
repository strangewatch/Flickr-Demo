package com.liamakakpo.demo.flickr.schedulers

import io.reactivex.Scheduler

class SingleSchedulerProvider(private val scheduler: Scheduler) : SchedulerProvider {

    override fun ui() = scheduler

    override fun computation() = scheduler

    override fun io() = scheduler
}