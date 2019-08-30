package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test3_anim

import android.animation.Animator
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.utils.ScreenUtils
import kotlinx.android.synthetic.main.activity_animator.*

class AnimatorActivity : AppCompatActivity() {
    private var openWidth: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)
        btnClose.setOnClickListener {
            setAnim()
            closeAnimator()
        }
        btnOpen.setOnClickListener {
            openWidth = btnOpen.width
            val view = ViewWrapper(btnOpen)
            ObjectAnimator.ofInt(view, "width", ScreenUtils.ScreenWidth(this)).setDuration(2000).start()
            openAnimator()
        }
        addLayoutAnim.setOnClickListener {
            val text = TextView(this)
            text.setBackgroundColor(Color.CYAN)
            animationLayout.addView(text)
        }
        animationLayout.post { startLayoutAnim() }
    }

    /**
     * 启动layoutanimation动画
     */
    fun startLayoutAnim() {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.animation_alpha)
        val lac = LayoutAnimationController(animation)
        lac.delay = 0.1f
        lac.order = LayoutAnimationController.ORDER_RANDOM
        animationLayout.layoutAnimation = lac
        animationLayout.layoutAnimationListener = aaa
    }

    var aaa = object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            Toast.makeText(this@AnimatorActivity, "end", Toast.LENGTH_SHORT).show()
        }

        override fun onAnimationStart(animation: Animation?) {
        }
    }

    fun openAnimator() {
        var animValue = creatAnim(imgAnimator, 0, 200)
        animValue.start()

    }

    fun closeAnimator() {
        var animValue = creatAnim(imgAnimator, 200, 0)
        animValue.addListener(object : Animator.AnimatorListener {

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        animValue.start()

    }

    fun creatAnim(image: View, start: Int, end: Int): ValueAnimator {
        val anim = ValueAnimator.ofInt(start, end)
        anim.addUpdateListener {
            val lp = image.layoutParams
            lp.height = (it.animatedValue).toString().toInt()
            image.layoutParams = lp
        }
        return anim
    }

    /**
     * 采用ValueAnimator，监听动画过程，自己实现属性的改变
     */
    fun setAnim() {
        val anim = ObjectAnimator.ofInt(1, 100)
        anim.addUpdateListener {
            val value = it.animatedValue
            var fraction: Float = it.animatedValue.toString().toFloat() / 100
            btnOpen.layoutParams.width = IntEvaluator().evaluate(fraction, ScreenUtils.ScreenWidth(this), openWidth)
            btnOpen.requestLayout()
        }
        anim.setDuration(1000).start()
    }

    /**
     * 使用ViewWrapper包装类包装一层，自定义get和set方法
     */
    class ViewWrapper {
        private var mTarget: View? = null

        constructor(mTarget: View?) {
            this.mTarget = mTarget
        }

        public fun getWidth(): Int {
            val width = mTarget?.width!!
            Log.e("www", width.toString())
            return mTarget?.width!!
        }

        public fun setWidth(width: Int) {
            mTarget?.layoutParams?.width = width
            mTarget?.requestLayout()
        }

    }
}