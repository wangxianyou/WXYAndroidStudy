package com.wxy.wxyandroidstudy.highlevel.test1_ipc.bindpool

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.wxy.bean.IQueryCode

/**
 * @author wxy
 * @description:
 * @date :2019-12-26 16:20
 */
class BindPoolService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    var bind = object : IQueryCode.Stub() {
        override fun getBind(code: Int): IBinder? {
            var iBinder:IBinder? = null
            when (code) {
                BinderPool.BINDER_COMPUTE -> iBinder = IComputerManager()
                BinderPool.BINDER_SECURITY -> iBinder = ISecyrityCenterManager()
            }
            return iBinder
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return bind
    }
}