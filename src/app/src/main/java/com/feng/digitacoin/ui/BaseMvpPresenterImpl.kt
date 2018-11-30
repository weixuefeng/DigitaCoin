package com.feng.digitacoin.ui

import java.lang.ref.WeakReference

/**
 * @time: 2018/11/9--3:39 PM
 * @description
 * @version $Rev$
 * @author weixuefeng@lubangame.com
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
open class BaseMvpPresenterImpl<V: BaseMvpView> : BaseMvpPresenter<V> {

    private var mViewWeakReference: WeakReference<V>? = null

    protected var mView : V? = null

    override fun attachView(view: V) {
        mViewWeakReference = WeakReference<V>(view)
        mView = mViewWeakReference?.get()
    }

    override fun detachView() {
        mViewWeakReference?.clear()
        mViewWeakReference = null
    }

}