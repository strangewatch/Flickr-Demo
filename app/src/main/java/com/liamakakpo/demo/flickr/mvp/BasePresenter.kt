package com.liamakakpo.demo.flickr.ui

abstract class BasePresenter<T : BaseView> : BaseContract.Presenter<T> {

    var view: T? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}