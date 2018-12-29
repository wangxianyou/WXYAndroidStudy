package com.wxy.wxyandroidstudy.LowerLevel.TheFirstPass.Test1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import com.wxy.wxyandroidstudy.R
import kotlinx.android.synthetic.main.lower_pass_one_test_one_activity_one.*

class ActivityTestOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("www","ActivityTestOne---- onCreate。。。。。")
        val view = LayoutInflater.from(this).inflate(R.layout.lower_pass_one_test_one_activity_one, null)
        setContentView(view)
        initListener()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("www","ActivityTestOne---- onDestroy。。。。。")
    }

    override fun onStart() {
        super.onStart()
        Log.e("www","ActivityTestOne---- onStart。。。。。")
    }

    override fun onStop() {
        super.onStop()
        Log.e("www","ActivityTestOne---- onStop。。。。。")
    }

    override fun onResume() {
        super.onResume()
        Log.e("www","ActivityTestOne---- onResume。。。。。")
    }

    override fun onPause() {
        super.onPause()
        Log.e("www","ActivityTestOne---- onPause。。。。。")
    }
    fun initListener() {
        btnIntent.setOnClickListener {
            ActivityTestTwo.startActivity(this)
        }
    }
}