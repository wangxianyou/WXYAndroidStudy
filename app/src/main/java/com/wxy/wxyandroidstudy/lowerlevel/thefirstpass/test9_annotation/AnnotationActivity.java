package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test9_annotation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wxy.base.MyApp;
import com.wxy.wxyandroidstudy.R;
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.activity.StandardActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 注解类
 */
public class AnnotationActivity extends AppCompatActivity {
    @BindView(R.id.test_tv)
    TextView textView;

    public static Context sContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        MyApp myApp = MyApp.Companion.getMyApp();
        sContext = this;
        textView.setText("ButterKnife 使用");
        textView.setOnClickListener(v -> {
            startActivity(new Intent(AnnotationActivity.this, StandardActivity.class));
        });
    }
}
