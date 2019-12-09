package com.wxy.wxyandroidstudy.highlevel.test1_ipc.aidl

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.RemoteCallbackList
import com.apkfuns.logutils.LogUtils
import com.wxy.bean.Book
import com.wxy.bean.IBookManager
import com.wxy.bean.IOnNewBookArrivedListener
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author wxy
 * @description:
 * @date :2019-12-06 10:24
 */
class AidlService : Service() {
    private var isStop: AtomicBoolean = AtomicBoolean(false)
    private var list: CopyOnWriteArrayList<Book> = CopyOnWriteArrayList()
    private var listenerList: RemoteCallbackList<IOnNewBookArrivedListener> = RemoteCallbackList()

    private var binder: Binder = object : IBookManager.Stub() {
        override fun getBookList(): MutableList<Book> {
            return list
        }

        override fun addBook(book: Book?) {
            list.add(book)
        }

        override fun registerListener(lis: IOnNewBookArrivedListener?) {
            listenerList.register(lis)
            LogUtils.e(listenerList.beginBroadcast())
            listenerList.finishBroadcast()
        }

        override fun unregisterListener(lis: IOnNewBookArrivedListener?) {
            listenerList.unregister(lis)
            LogUtils.e(listenerList.beginBroadcast())
            listenerList.finishBroadcast()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.e("service  onCreate")
        MyThread().start()
    }

    override fun onDestroy() {
        super.onDestroy()
        isStop.set(true)
        LogUtils.e("service  onDestroy")
    }

    internal inner class MyThread : Thread() {
        override fun run() {
            super.run()

            while (!isStop.get()) {

                sleep(5000)
                val count = listenerList.beginBroadcast()
                if (count > 0) {
                    for (index in 0 until count) {
                        listenerList.getBroadcastItem(index).onNewBookArrived(Book(index, "new Book$index"))
                    }
                }
                listenerList.finishBroadcast()
            }

        }
    }
}