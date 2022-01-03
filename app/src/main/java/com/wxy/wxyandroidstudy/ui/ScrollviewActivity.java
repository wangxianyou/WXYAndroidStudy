package com.wxy.wxyandroidstudy.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.wxy.view.MyScrollView;
import com.wxy.wxyandroidstudy.R;

/**
 * @Description:
 * @Author: wangxianyou
 * @CreateDate: 2021/12/20
 */
public class ScrollviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        NestedScrollView scrollview = (NestedScrollView) findViewById(R.id.scrollview);
//        SeekBar seekBar = (SeekBar) findViewById(R.id.mySeekBar);
//        ScrollBindHelper.bind(seekBar,scrollview);

        MyScrollView scrollbar = findViewById(R.id.scrollbar);
        scrollbar.attachScrollView(scrollview);
    }
}
