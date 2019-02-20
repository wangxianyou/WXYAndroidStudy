package com.wxy.wxyandroidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.BaseActivity;
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.activity.SingleTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R2.id.click)
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SingleTaskActivity.class));
            }
        });
    }
}
