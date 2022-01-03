package com.wxy.base

import android.app.Application
import com.squareup.leakcanary.LeakCanary
//import com.bilibili.magicasakura.manage.SkinCompatManager
//import com.bilibili.magicasakura.manage.SkinNoneLoader
//import com.bilibili.magicasakura.manage.SkinPrefixBuildInLoader
import com.tencent.bugly.crashreport.CrashReport


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
        CrashReport.initCrashReport(applicationContext, "e4e60b97cc", false)
        initSkin()
    }

    private fun initSkin() {
        // 基础控件换肤初始化
//        SkinCompatManager.withoutActivity(this)
//                .addStrategy(SkinPrefixBuildInLoader())
//                .addStrategy(SkinNoneLoader())
//                .addStrategy(SkinZipLoader())
//                .loadSkin(SkinZipUtils.SKIN_VERSION_DTP)
    }
    companion object {
        private var instance: MyApp? = null
        fun getMyApp() = instance
    }
}