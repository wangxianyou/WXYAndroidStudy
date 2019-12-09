package com.wxy.wxyandroidstudy.highlevel.test1_ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.apkfuns.logutils.LogUtils
import com.wxy.base.AppConst
import com.wxy.bean.Book
import com.wxy.bean.IBookManager
import com.wxy.bean.IOnNewBookArrivedListener
import com.wxy.wxyandroidstudy.R
import kotlinx.android.synthetic.main.test.*

/**
 * @author wxy
 * @description:
 * @date :2019-12-06 10:03
 */
class AidlClientActivity :AppCompatActivity(){


    private var handler:Handler = Handler{
        if (it.what == AppConst.MSG_FROM_SERVICE) {
            LogUtils.e(it.obj)
        }
        false
    }

    var iBookManager:IBookManager? = null
    private var conn = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iBookManager = IBookManager.Stub.asInterface(service)
//            LogUtils.e(iBookManager.bookList)
//            iBookManager.addBook(Book(1,"大话西游"))
//            iBookManager.addBook(Book(2,"小虎"))
//            LogUtils.e(iBookManager.bookList)

            iBookManager?.registerListener(listener)

        }
    }

    private var listener: IOnNewBookArrivedListener = object :IOnNewBookArrivedListener.Stub(){
        override fun onNewBookArrived(book: Book?) {
            handler.obtainMessage(AppConst.MSG_FROM_SERVICE,book).sendToTarget()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        test_tv.setOnClickListener {
            bindService(Intent(this,AidlService::class.java),conn,Context.BIND_AUTO_CREATE)
        }
        stop.setOnClickListener {
            iBookManager?.unregisterListener(listener)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        iBookManager?.unregisterListener(listener)
        unbindService(conn)
    }
}