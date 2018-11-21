package com.feng.digitacoin.extension

import android.content.Context
import android.support.annotation.IntegerRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/22--12:11 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */

// context extension
fun Context.toast(message: String, length : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.toast(@StringRes messageId: Int, length : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageId, length).show()
}

//view group extension
fun ViewGroup.inflate(@LayoutRes resId: Int) : View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}

//log
fun Context.loge(message: String, tag: String = "defaultTAG") {
    Log.e(tag, message)
}



