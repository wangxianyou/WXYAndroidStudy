package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test5_handle

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity

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
        val msg = Message()
        msg.what = 1
        msg.arg1 = 2
        mHandle.sendMessage(msg)
        mHandle.obtainMessage()
    }
}