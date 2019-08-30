package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test2_view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {
    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {

            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                int width = setWidth(childCount);
                int height = setHeight(childCount);
                setMeasuredDimension(width, height);
            }else if(widthMode == MeasureSpec.AT_MOST){
                int width = setWidth(childCount);
                setMeasuredDimension(width,heightSize);
            }else if(heightMode == MeasureSpec.AT_MOST){
                int height = setHeight(childCount);
                setMeasuredDimension(widthSize,height);
            }
        }


    }

    private int setHeight(int childCount) {
        int height = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            height += childAt.getMeasuredHeight();
        }
        return height;
    }

    private int setWidth(int childCount) {
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getWidth() > maxWidth) {
                maxWidth = childAt.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childCount = getChildCount();
        int height = i1;
        for (int j = 0; j < childCount; j++) {
            View childAt = getChildAt(j);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            childAt.layout(i,height,i+measuredWidth,height+measuredHeight);
            height += measuredHeight;
        }
    }

}
