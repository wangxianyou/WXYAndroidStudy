package com.wxy.base

import android.app.Application
import com.squareup.leakcanary.LeakCanary


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    companion object {
        private var instance: MyApp? = null
        fun getMyApp() = instance
    }
}