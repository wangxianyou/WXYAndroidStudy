package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test6_thread

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors


class AsynTaskTest : AppCompatActivity() {

    class MyAsyncTask(private val mName: String) : AsyncTask<String, Int, String>() {

        override fun doInBackground(vararg params: String):String {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            Log.i(mName, dateFormat.format(Date(System.currentTimeMillis())))
            return ""
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        MyAsyncTask("wwwthread111").execute()
//        MyAsyncTask("wwwthread222").execute()
//        MyAsyncTask("wwwthread333").execute()


        MyAsyncTask("wwwthread111").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        MyAsyncTask("wwwthread222").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        MyAsyncTask("wwwthread333").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        MyThread().run()
        MyThread().start()

        val fixedThreadPool = Executors.newFixedThreadPool(5)
        fixedThreadPool.execute(object :Runnable {
            override fun run() {

            }
        })
        Executors.newCachedThreadPool()
        Executors.newSingleThreadExecutor()
        Executors.newScheduledThreadPool(5)

    }


    class MyThread :Thread(){
        override fun run() {
            super.run()
        }
    }

}