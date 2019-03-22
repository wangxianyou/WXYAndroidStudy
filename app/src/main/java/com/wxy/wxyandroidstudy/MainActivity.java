package com.wxy.wxyandroidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wxy.demo.iflytek.SpeakToTxtActivity;
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R2.id.click)
    Button click;
    @BindView(R2.id.btn_ifly)
    Button btnIfly;
    private String[] str = {"讯飞语音听写", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        btnIfly.setOnClickListener(this);
        click.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click:
                startActivity(new Intent(MainActivity.this, com.iflytek.voicedemo.MainActivity.class));
                break;
            case R.id.btn_ifly:
                SpeakToTxtActivity.startActivity(this);
                break;
            default:
                break;
        }
    }
}
