package com.wxy.wxyandroidstudy.highlevel.test1_ipc.bindpool

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.apkfuns.logutils.LogUtils
import com.wxy.base.MyApp
import com.wxy.bean.IQueryCode
import java.util.concurrent.CountDownLatch

/**
 * @author wxy
 * @description:
 * @date :2019-12-26 16:06
 */
class BinderPool private constructor() {
    var mBind: IQueryCode? = null
    var mDownLatch: CountDownLatch? = null
    var context = MyApp.getMyApp()
    var reConn:IBinder.DeathRecipient? = null
    companion object {
        val BINDER_COMPUTE = 1
        val BINDER_SECURITY = 2
        val instance: BinderPool by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { BinderPool() }

    }

    init {
        reConn = IBinder.DeathRecipient {
            mBind?.asBinder()?.unlinkToDeath(reConn,0)
            mBind = null
            connectBindService()
        }
        connectBindService()
    }

    private fun connectBindService() {
        mDownLatch = CountDownLatch(1)

        val conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                LogUtils.e("onServiceConnected")
                mBind = IQueryCode.Stub.asInterface(service)
                mBind?.asBinder()?.linkToDeath(reConn,0)

                mDownLatch?.countDown()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                LogUtils.e("onServiceDisconnected")
            }
        }
        val intent = Intent(context, BindPoolService::class.java)
        context?.bindService(intent,conn,Context.BIND_AUTO_CREATE)
        mDownLatch?.await()
    }

    fun queryBind(code: Int) :IBinder?{
        return mBind?.getBind(code)
    }


}