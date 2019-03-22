package com.wxy.demo.iflytek

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.R2

class SpeakToTxtActivity : AppCompatActivity(), View.OnClickListener {
    @BindView(R2.id.tv_speak_show)
    var speakShow: TextView? = null
    @BindView(R2.id.btn_start_speak)
    var startSpeak: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speak_to_txt)
        ButterKnife.bind(this)
        speakShow?.text = ""
        startSpeak?.setOnClickListener(this)

    }

    /**
     * 弹出语音识别转文字dialog
     */
    fun showSpeakDialog() {
        var dialog = SpeakToTxtDialog()
        dialog.show(supportFragmentManager,"dialog")
    }

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, SpeakToTxtActivity::class.java))
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_start_speak -> showSpeakDialog()
            else -> ""
        }

    }
}