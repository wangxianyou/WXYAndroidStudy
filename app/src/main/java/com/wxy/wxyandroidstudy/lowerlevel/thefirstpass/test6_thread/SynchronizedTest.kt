package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test6_thread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SynchronizedTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for (i in 0..3) {
            val thread: Thread = MyThread()
            thread.start()
        }

    }

    class MyThread : Thread() {
        override fun run() {
            super.run()
//            val syn = Sync()
            Sync.test()
        }
    }

    class Sync {
        companion object {
            @Synchronized
            fun test() {
//            synchronized(Sync::class.java) {

                println("test开始..")
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                println("test结束..")
//            }
            }
        }

    }

}
