package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test2.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wxy.wxyandroidstudy.R;

import androidx.annotation.Nullable;

/**
 * 宽度设置为match_parent时，宽度长于高度，不是长方形
 */
//todo 1.父view是RelativeLayout时不是正方形  2. onMeasure执行多次
public class TestViewSpuare extends View {

    private int defaultSize;

    public TestViewSpuare(Context context) {
        this(context, null);
    }

    public TestViewSpuare(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestViewSpuare(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TestViewSpuare);
        defaultSize = array.getDimensionPixelSize(R.styleable.TestViewSpuare_defaultSize, 100);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int withSize = getMySize(widthMeasureSpec, "宽");
        int heightSize = getMySize(heightMeasureSpec, "高");
        if (withSize < heightSize) {
            Log.e("www", "onMeasure: withSize = " + withSize + "--heightSize=" + heightSize);
            setMeasuredDimension(withSize, withSize);
        } else {
            Log.e("www", "onMeasure: withSize = " + withSize + "--heightSize=" + heightSize);
            setMeasuredDimension(heightSize, heightSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        Log.e("www", "onDraw: r = "+r);
        int x = r;
        int y = r;
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, r, paint);
    }

    public int getMySize(int measureSpec, String str) {
        int result = defaultSize;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        Log.e("www", "getSize: size = " + size + "---mode = " + mode + "--defaultSize" + defaultSize + "-------" + str);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = defaultSize;
                break;
            default:
                break;
        }
        return result;
    }
}
