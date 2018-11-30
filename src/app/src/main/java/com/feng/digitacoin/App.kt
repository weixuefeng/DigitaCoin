package com.feng.digitacoin

import android.app.Application
import com.feng.digitacoin.extension.loge
import com.squareup.leakcanary.LeakCanary

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            loge("install leak")
            LeakCanary.install(this)
        }
    }
}
