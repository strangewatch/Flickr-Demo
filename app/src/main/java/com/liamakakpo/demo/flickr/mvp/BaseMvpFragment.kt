package com.liamakakpo.demo.flickr.mvp

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liamakakpo.demo.flickr.ui.BasePresenter
import com.liamakakpo.demo.flickr.ui.BaseView

abstract class BaseMvpFragment<V : BaseView, P : BasePresenter<V>> : Fragment(), BaseView {

    protected var presenter: P? = null

    protected abstract fun createPresenter(): P

    @LayoutRes
    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.let {
            it.attachView(this as V)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.let {
            it.detachView()
        }
        this.presenter = null
    }
}
