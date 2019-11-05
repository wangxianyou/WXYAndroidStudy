package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test5_handle

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.wxy.wxyandroidstudy.R
import kotlinx.android.synthetic.main.test.*

class HandleTestActivity : AppCompatActivity() {
    companion object {
        private const val MSG = 1
        private var mHandle = Handler {
            if (it.what == MSG) {

            }
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        val msg = Message()
        msg.what = MSG
        msg.arg1 = 2
        mHandle.sendMessage(msg)
        mHandle.obtainMessage()

        test_tv.text = getInfo()
    }

    fun getInfo():String{
        return "main 文本测试"
    }

}