package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test4_file

import android.database.sqlite.SQLiteDatabase
import java.util.concurrent.atomic.AtomicInteger

class MyDataManager {

    companion object {
        private var mMyHelper: MySqliteHelper? = null
        private var manager: MyDataManager? = null

        @Synchronized
        fun initInstance(helper: MySqliteHelper) {
            if (manager == null) {
                mMyHelper = helper
                manager = MyDataManager()
            }
        }

        @Synchronized
        fun getInstance(): MyDataManager {

            if (manager == null) {
                throw Exception("异常")
            }
            return manager as MyDataManager
        }
    }

    private var mDataBase: SQLiteDatabase? = null
    private var atomicInteger: AtomicInteger = AtomicInteger()

    @Synchronized
    fun openDatabase(): SQLiteDatabase? {
        if (atomicInteger.incrementAndGet() == 1) {
            mDataBase = mMyHelper?.writableDatabase
        }
        return mDataBase
    }

    @Synchronized
    fun closeDatabase() {
        if (atomicInteger.decrementAndGet() == 0) {
            mDataBase?.close()
        }
    }


}