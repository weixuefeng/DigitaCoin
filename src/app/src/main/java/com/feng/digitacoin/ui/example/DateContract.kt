package com.feng.digitacoin.ui.example

import com.feng.digitacoin.ui.BaseMvpPresenter
import com.feng.digitacoin.ui.BaseMvpView

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/22--12:55 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
object DateContract {

    interface View : BaseMvpView {
        fun showList(list: List<Int>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadListData()
    }
}