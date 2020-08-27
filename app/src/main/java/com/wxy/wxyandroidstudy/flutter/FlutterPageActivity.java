package com.wxy.wxyandroidstudy.flutter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @author wxy
 * @description:
 * @date :2020-06-03 13:49
 */
public class FlutterPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过FlutterView引入Flutter编写的页面
//        View flutterView = Flutter.createView(this, getLifecycle(), "route1");
//        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(600, 800);
//        layout.leftMargin = 100;
//        layout.topMargin = 200;
//        addContentView(flutterView, layout);
    }
}
