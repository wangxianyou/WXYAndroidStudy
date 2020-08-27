package com.wxy.wxyandroidstudy.highlevel.test2_customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * @author wxy
 * @description:
 * @date :2020-04-17 16:04
 */


class CircleView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attr, defStyleAttr) {
    private var mPaint = Paint()

    init {
        mPaint.flags = Paint.ANTI_ALIAS_FLAG
        mPaint.color = Color.BLUE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val wSize = MeasureSpec.getSize(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        if (wMode == MeasureSpec.AT_MOST && hMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400,400)
        }else if (wMode == MeasureSpec.AT_MOST ){
            setMeasuredDimension(400,hSize)
        }else if (hMode == MeasureSpec.AT_MOST ){
            setMeasuredDimension(wSize,400)
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val w = width - paddingLeft - paddingRight
        val h = height - paddingBottom - paddingTop
        val r = min(w,h) / 2
        canvas?.drawCircle(paddingLeft+w.toFloat()/2,paddingTop+h.toFloat()/2,r.toFloat(),mPaint)
    }
}