package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.BaseActivity
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.TestActivity
import kotlinx.android.synthetic.main.lower_pass_one_test_one_activity_one.*

class OtherActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lower_pass_one_test_one_activity_one)
        btnIntent.visibility = View.VISIBLE
        btnIntent.setOnClickListener {
            var intent = Intent(this,TestActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}