package com.wxy.demo.iflytek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.base.MyApp


class SpeakToTxtDialog :DialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_speak_to_txt, container)
        return view
    }

    override fun onStart() {
        super.onStart()
        val dialogHeight = (MyApp.getMyApp()!!.getResources().getDisplayMetrics().heightPixels * 0.8) as Int
        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight)
        dialog!!.setCanceledOnTouchOutside(true) //点击边际可消失

    }
}