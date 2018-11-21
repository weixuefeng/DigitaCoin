package com.feng.digitacoin.ui.example

import android.os.Bundle
import com.feng.digitacoin.R
import com.feng.digitacoin.extension.loge
import com.feng.digitacoin.ui.BaseActivity

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/20--2:56 PM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class ChooseDateActivity : BaseActivity<DateContract.View, ChoosePresenter>(), DateContract.View {

    override var mPresenter: ChoosePresenter = ChoosePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_date)
        mPresenter.loadListData()
        showError("error")
    }

    override fun showList(list: List<Int>) {
        loge(list.toString())
    }
}
