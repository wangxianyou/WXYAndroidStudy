package com.wxy.wxyandroidstudy.xx;

import android.os.Bundle;
import android.widget.TextView;

import com.wxy.annotion.InjectView;
import com.wxy.utils.InjectViewUtils;
import com.wxy.wxyandroidstudy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author wxy
 * @description:
 * @date :2020/8/27 4:31 PM
 */
public class InjectViewActivity extends AppCompatActivity {
    @InjectView(R.id.view)
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        InjectViewUtils.init(this);
        tv.setText("自定义注解成功了！！！");
    }
}
