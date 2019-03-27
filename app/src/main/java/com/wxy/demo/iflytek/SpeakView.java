package com.wxy.demo.iflytek;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.wxy.wxyandroidstudy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
public class SpeakView extends AppCompatImageView {
    public SpeakView(Context context) {
        super(context);
    }

    public SpeakView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeakView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setImageResource(R.drawable.ic_launcher_background);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                setImageResource(R.drawable.ic_launcher_foreground);
                break;
            default:
                break;
        }
        return true;
    }

}
