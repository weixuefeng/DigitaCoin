package com.feng.digitacoin.ui.example

import com.feng.digitacoin.ui.BaseMvpPresenterImpl

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/22--12:37 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class ChoosePresenter : BaseMvpPresenterImpl<DateContract.View>(), DateContract.Presenter {

    override fun loadListData() {
        val list = listOf<Int>(1, 2, 3)
        mView!!.showList(list)
    }

}