package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("www", "****  onCreate方法  ****" + "class:" + this.localClassName + "--task:" + this.taskId + "--hashcode:" + this.hashCode()+"--affinity:"+getAffinity())
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("www", "****  onNewIntent  ****" + "class:" + this.localClassName + "--task:" + this.taskId + "--hashcode:" + this.hashCode()+"--affinity:"+getAffinity())
    }

    fun getAffinity():String {
        var activityInfo = packageManager.getActivityInfo(componentName, 0)
        return activityInfo.taskAffinity
    }

}