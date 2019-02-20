package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test2.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 宽度设置为match_parent时，宽度长于高度，不是长方形
 */
public class TestViewSpuare extends View {
    public TestViewSpuare(Context context) {
        super(context);
    }

    public TestViewSpuare(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewSpuare(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int withSize = getSize(widthMeasureSpec);
        int heightSize = getSize(heightMeasureSpec);
        if (withSize < heightSize) {
            heightSize = withSize;
        } else {
            withSize = heightSize;
        }
        setMeasuredDimension(withSize, heightSize);
    }

    public int getSize(int measureSpec) {
        int result = 100;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = size;
                break;
            default:
                break;
        }
        return result;
    }
}
