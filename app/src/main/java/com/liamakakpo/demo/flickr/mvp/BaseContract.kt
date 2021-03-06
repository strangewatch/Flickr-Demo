package com.liamakakpo.demo.flickr.ui

interface BaseContract {

    interface View

    interface Presenter<in T : View> {

        fun attachView(view: T)

        fun detachView()
    }
}