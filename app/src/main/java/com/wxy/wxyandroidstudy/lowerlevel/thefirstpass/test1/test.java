package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wxy.wxyandroidstudy.R;
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1.activity.SingleTaskActivity;

public class test extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lower_pass_one_test_one_activity_one);
        String str = "1||";
        String[] arr = str.split("\\|");
        findViewById(R.id.btnIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(test.this, SingleTaskActivity.class));
            }
        });
    }
}
