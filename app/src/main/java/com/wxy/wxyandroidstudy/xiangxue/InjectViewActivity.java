package com.wxy.wxyandroidstudy.xiangxue;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wxy.annotation.InjectExtraView;
import com.wxy.annotation.InjectView;
import com.wxy.utils.InjectViewUtils;
import com.wxy.wxyandroidstudy.R;

import java.util.Arrays;
import java.util.List;

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
    @InjectExtraView("name")
    String name;
    @InjectExtraView(("isBoy"))
    boolean isBoy;
    @InjectExtraView(("str"))
    Object[] strs;
    @InjectExtraView(("list"))
    List list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        InjectViewUtils.initBindView(this);
        InjectViewUtils.injectAutowired(this);
        tv.setText("自定义注解成功了！！！name="+name+"，isBoy="+isBoy+",strs="+ Arrays.toString(strs)+",list="+new Gson().toJson(list));
    }
}
