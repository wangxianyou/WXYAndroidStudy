package com.wxy.wxyandroidstudy.xiangxue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wxy.utils.DensityUtils;
import com.wxy.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxy
 * @description:
 * @date :2020/11/4 11:09 AM
 */
public class FlowLayout extends ViewGroup {
    private List<List<View>> lines = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private List<Integer> heights = new ArrayList<>();
    private int horizantolSpace;
    private int verticalSpace;
    private Context mContext;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        horizantolSpace = DensityUtils.dp2px(context,16);
        verticalSpace = DensityUtils.dp2px(context,8);
        mContext = context;
    }

    public void clearData() {
        lines.clear();
        heights.clear();
        views.clear();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        clearData();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        widthSize = widthSize > ScreenUtils.ScreenWidth(mContext) ? ScreenUtils.ScreenWidth(mContext):widthSize;

        int childCount = getChildCount();
        int widthUsed = getPaddingLeft();
        int lineHeight = 0;

        int needWidth = 0;
        int needHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();
            int childMeasureSpecW = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), params.width);
            int childMeasureSpecH = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), params.height);
            childView.measure(childMeasureSpecW, childMeasureSpecH);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            if (widthUsed + childWidth +horizantolSpace > widthSize) {

                needWidth = Math.max(needWidth, widthUsed);
                needHeight += lineHeight+verticalSpace;

                lines.add(views);

                heights.add(lineHeight);
                widthUsed = getPaddingLeft();
                lineHeight = 0;
                views = new ArrayList<>();

            }



            widthUsed += childWidth+horizantolSpace;
            lineHeight = Math.max(lineHeight, childHeight);
            views.add(childView);

            if (i == childCount - 1) {
                lines.add(views);
                heights.add(lineHeight);
                needWidth = Math.max(needWidth, widthUsed);
                needHeight += lineHeight+verticalSpace;
            }

        }


        int realWidth = needWidth;
        int realHeight = needHeight;

        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int size = lines.size();
        int needH = 0;
        for (int i = 0; i < size; i++) {
            List<View> views = lines.get(i);
            int paddingLeft = getPaddingLeft();

            for (int j = 0; j < views.size(); j++) {
                View view = views.get(j);
                int childWidth = view.getMeasuredWidth();
                int childHeight = view.getMeasuredHeight();
                view.layout(paddingLeft, needH, paddingLeft + childWidth, needH + childHeight);
                paddingLeft += childWidth+horizantolSpace;
            }
            needH += heights.get(i)+verticalSpace;
        }

    }
}
