package com.wxy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.wxy.wxyandroidstudy.R;

/**
 * @Description:
 * @Author: wangxianyou
 * @CreateDate: 2021/12/20
 */
public class MyScrollView extends View {
    private int mVerticalThumbHeight;//滑块高度
    private int mVerticalThumbWidth;//滑块宽度
    private int mVerticalThumbTop;//滑块当前起点位置
    private Drawable mThumbDrawable;//滑块drawable
    private Drawable mTrackDrawable;//滑道drawable

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mThumbDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.progress_bar_ic);
        mTrackDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.progress_bar_bg);
        mVerticalThumbWidth = 20;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvas == null) {
            return;
        }

        //滑块的top
        int top = mVerticalThumbTop;
        //滑块的bottom
        int bottom = mVerticalThumbTop + mVerticalThumbHeight;

        //先绘制滑道
        mTrackDrawable.setBounds(0, 0, mVerticalThumbWidth, getMeasuredHeight());
        mTrackDrawable.draw(canvas);

        //再绘制滑块
        mThumbDrawable.setBounds(0, top, mVerticalThumbWidth, bottom);
        mThumbDrawable.draw(canvas);
    }

    /**
     * 与ScrollView绑定
     * @param nestedScrollView 绑定的ScrollView,由于默认的ScrollView不自带滑动监听,所以此处用的是NestedScrollView
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void attachScrollView(NestedScrollView nestedScrollView) {

        nestedScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                calculate(nestedScrollView);
            }
        });

        View child = nestedScrollView.getChildAt(0);
        //由于一般ScrollView的子View都是TextView，所以我们需要在TextView的内容变换之后重新测量
        if (child instanceof TextView) {

            ((TextView) child).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    calculate(nestedScrollView);
                }
            });
        }
        //调用太早无法获取测量高度
        post(new Runnable() {
            @Override
            public void run() {
                calculate(nestedScrollView);
            }
        });

    }

    private void calculate(NestedScrollView nestedScrollView) {
        //ScrollView的高度
        int visibleHeight = nestedScrollView.getMeasuredHeight();
        //ScrollView内部的内容高度
        int contentHeight = 0;
        View childAt = nestedScrollView.getChildAt(0);
        if (childAt != null) {
            contentHeight = childAt.getHeight();
        }
        //若不需要滚动，则直接隐藏
        if (contentHeight <= visibleHeight) {
            setVisibility(INVISIBLE);
            return;
        } else {
            setVisibility(VISIBLE);
        }
        //当前ScrollView内容滚动的距离
        int scrollY = nestedScrollView.getScrollY();
        //计算出滑块的高度
//        mVerticalThumbHeight = measuredHeight * visibleHeight / contentHeight
        mVerticalThumbHeight = 100;
        //滑块的top值范围是从0到{滑道高度-滑块高度}
        mVerticalThumbTop =
                (getMeasuredHeight() - mVerticalThumbHeight) * scrollY / (contentHeight - visibleHeight);
        invalidate();
    }

}
