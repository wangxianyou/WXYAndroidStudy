package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.wxy.wxyandroidstudy.R;

public class test extends BaseActivity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        TextView tv = findViewById(R.id.test_tv);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("www", "onTouch: x="+motionEvent.getX()+"---y="+ motionEvent.getY());
                        break;
                }
                return false;
            }
        });
    }
}
