package com.wxy.wxyandroidstudy.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class ScreenUtils {
    companion object {
        fun ScreenWidth(context: Context):Int{
            val windowManager:WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }
}