package com.wxy.wxyandroidstudy.highlevel.test1_ipc.bindpool

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apkfuns.logutils.LogUtils
import com.wxy.bean.IComputer

/**
 * @author wxy
 * @description:
 * @date :2019-12-26 17:27
 */
class BindPoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread(Runnable {
            val comBind = BinderPool.instance.queryBind(BinderPool.BINDER_COMPUTE)
            if (comBind == null) {
                LogUtils.e("comBind == null")
            }
            val iComputer = IComputer.Stub.asInterface(comBind)
            val add = iComputer.add(1, 2)
            LogUtils.e(add)
        }).start()
    }
}