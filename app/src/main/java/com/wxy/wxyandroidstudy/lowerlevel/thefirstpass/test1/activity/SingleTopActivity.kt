package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.activity

import android.content.Intent
import android.os.Bundle
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.BaseActivity
import kotlinx.android.synthetic.main.lower_pass_one_test_one_activity_one.*

class SingleTopActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lower_pass_one_test_one_activity_one)
        btnIntent.setOnClickListener {
            var intent = Intent(this,SingleTopActivity::class.java)
            startActivity(intent)
        }
    }
}