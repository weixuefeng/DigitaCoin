package com.feng.digitacoin.ui.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.feng.digitacoin.R
import com.feng.digitacoin.extension.toast

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/23--12:07 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        toast("haha")
    }
}