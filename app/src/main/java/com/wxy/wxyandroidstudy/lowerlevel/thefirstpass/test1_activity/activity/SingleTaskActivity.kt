package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.activity

import android.content.Intent
import android.os.Bundle
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.BaseActivity
import kotlinx.android.synthetic.main.lower_pass_one_test_one_activity_one.*

class SingleTaskActivity:BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lower_pass_one_test_one_activity_one)
        btnIntent.setOnClickListener {
            var intent = Intent(this,SingleTaskActivity::class.java)
            startActivity(intent)
        }
        btnOtherIntent.setOnClickListener {
            var intent = Intent(this,OtherActivity::class.java)
            startActivity(intent)
        }
    }
}