package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test9_annotation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.wxy.base.AppConst;
import com.wxy.base.MyApp;
import com.wxy.bean.User;
import com.wxy.wxyandroidstudy.R;
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.activity.StandardActivity;
import com.wxy.utils.MyUtils;
import com.wxy.utils.TestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

    public Context sContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        MyApp myApp = MyApp.Companion.getMyApp();
        sContext = this;
        textView.setText("ButterKnife 使用");
        TestUtils.num = 2;
        LogUtils.e("AnnotationActivity num="+TestUtils.num);
        textView.setOnClickListener(v -> {
            startActivity(new Intent(AnnotationActivity.this, StandardActivity.class));
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        new MyThread().start();
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            User user = new User("wxy",28,true);
            File file = new File(AppConst.CHAPTER_2_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }

            File fileCache = new File(AppConst.CACHE_FILE_PATH);
            ObjectOutputStream outputStream = null;
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(fileCache));
                outputStream.writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                MyUtils.close(outputStream);
            }

        }
    }
}
