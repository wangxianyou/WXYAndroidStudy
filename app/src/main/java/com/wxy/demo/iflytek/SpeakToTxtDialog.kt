package com.wxy.demo.iflytek

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.wxy.wxyandroidstudy.R


class SpeakToTxtDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_speak_to_txt, container)
        return view
    }

    override fun onStart() {
        super.onStart()
        val dialogHeight = (activity!!.getResources().getDisplayMetrics().widthPixels * 0.8)
        val window = dialog!!.window!!
        window.setGravity(Gravity.BOTTOM)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogHeight.toInt())
        dialog!!.setCanceledOnTouchOutside(true) //点击边际可消失
    }

}