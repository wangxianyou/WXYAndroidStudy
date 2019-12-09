package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.activity

import android.content.Intent
import android.os.Bundle
import com.apkfuns.logutils.LogUtils
import com.wxy.base.AppConst
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.BaseActivity
import com.wxy.wxyandroidstudy.utils.MyUtils
import com.wxy.wxyandroidstudy.utils.TestUtils
import kotlinx.android.synthetic.main.lower_pass_one_test_one_activity_one.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.ObjectInputStream

class StandardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lower_pass_one_test_one_activity_one)
        LogUtils.e("StandardActivity num=" + TestUtils.num)
        btnIntent.setOnClickListener {
            var intent = Intent(this, StandardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        MyThread().start()
    }

    internal class MyThread : Thread() {
        override fun run() {
            super.run()
            val fileCache = File(AppConst.CACHE_FILE_PATH)
            var outputStream: ObjectInputStream? = null
            try {
                outputStream = ObjectInputStream(FileInputStream(fileCache))
                val readObject = outputStream.readObject()
                LogUtils.e(readObject)
            } catch (e: IOException) {
                e.printStackTrace()
                LogUtils.e(e)
            } finally {
                MyUtils.close(outputStream)
            }

        }
    }
}