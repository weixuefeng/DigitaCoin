package com.feng.digitacoin.utils

import android.util.Log
import com.feng.digitacoin.BuildConfig

class Logger{
    fun checkDebug() : Boolean = BuildConfig.DEBUG
}

fun Logger.d(message: String) {
    if(checkDebug()){
        Log.d("logger", message)
    }
}