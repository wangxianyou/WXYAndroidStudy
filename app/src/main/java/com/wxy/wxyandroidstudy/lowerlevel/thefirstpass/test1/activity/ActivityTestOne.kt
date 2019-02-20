package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
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

    override fun onRestart() {
        super.onRestart()
        Log.e("www","ActivityTestTwo---- onRestart。。。。。")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.e("www","onCreateOptionsMenu。。。。。")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("www","ActivityTestOne---- onNewIntent。。。。。")
    }
}