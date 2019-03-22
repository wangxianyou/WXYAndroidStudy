package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test3

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.wxy.wxyandroidstudy.R
import kotlinx.android.synthetic.main.activity_animotion.*

class AnimationActivity : AppCompatActivity() {
    private var list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animotion)
//        showAlpha()

        initData()
        var lac = LayoutAnimationController(AnimationUtils.loadAnimation(this,R.anim.animation_alpha))
        lac.delay = 0.5f
        lac.order = LayoutAnimationController.ORDER_NORMAL
        lvView.layoutAnimation = lac
        lvView.adapter = ArrayAdapter(this,R.layout.test,R.id.test_tv,list)
        lvView.startLayoutAnimation()
    }
//    private fun showAlpha(){
//        val animation = AnimationUtils.loadAnimation(this, R.anim.animation_alpha)
//        animation.fillAfter = true
//        imgView.startAnimation(animation)
//    }

    fun initData() {
        for (i in 1..20){
            list.add("hahhahah"+i)
        }
    }


}