package com.wxy.wxyandroidstudy.LowerLevel.TheFirstPass.Test1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wxy.wxyandroidstudy.R

class ActivityTestTwo:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("www","ActivityTestTwo---- onCreate。。。。。")
        setContentView(R.layout.lower_pass_one_test_one_activity_two)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("www","ActivityTestTwo---- onDestroy。。。。。")
    }

    override fun onStart() {
        super.onStart()
        Log.e("www","ActivityTestTwo---- onStart。。。。。")
    }

    override fun onStop() {
        super.onStop()
        Log.e("www","ActivityTestTwo---- onStop。。。。。")
    }

    override fun onResume() {
        super.onResume()
        Log.e("www","ActivityTestTwo---- onResume。。。。。")
    }

    override fun onPause() {
        super.onPause()
        Log.e("www","ActivityTestTwo---- onPause。。。。。")
    }
    companion object {
        fun startActivity(context: Context){
            var intent = Intent(context,this::class.java)
            context.startActivity(intent)
        }
    }
}