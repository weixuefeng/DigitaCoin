package com.feng.digitacoin.ui

/**
 * @time: 2018/11/9--3:38 PM
 * @description
 * @version $Rev$
 * @author weixuefeng@lubangame.com
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
interface BaseMvpPresenter<in V: BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}