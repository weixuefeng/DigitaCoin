package com.feng.digitacoin.ui

import android.content.Context
import android.support.annotation.StringRes

/**
 * @time: 2018/11/9--3:36 PM
 * @description
 * @version $Rev$
 * @author weixuefeng@lubangame.com
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
interface BaseMvpView{

    fun getContext() : Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(message: String)

    fun showMessage(@StringRes stringResId: Int)
}