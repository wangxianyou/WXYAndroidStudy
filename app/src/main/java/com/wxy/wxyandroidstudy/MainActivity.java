package com.wxy.wxyandroidstudy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxy.bean.User;
import com.wxy.demo.iflytek.SpeakToTxtActivity;
import com.wxy.utils.ScreenUtils;
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test1_activity.BaseActivity;
import com.wxy.wxyandroidstudy.xx.InjectViewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.flutter.embedding.android.FlutterActivity;

/**
 * @author wxy
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R2.id.click)
    Button click;
    @BindView(R2.id.dialogClick)
    Button dialogClick;
    @BindView(R2.id.btn_ifly)
    Button btnIfly;
    @BindView(R2.id.mFlutter)
    Button mFlutter;
    @BindView(R2.id.mAnno)
    Button mAnno;

    private String[] str = {"讯飞语音听写", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float x = metrics.xdpi;
        Log.e("www", "屏幕密度： " + x);

    }

    private void initListener() {
        btnIfly.setOnClickListener(this);
        click.setOnClickListener(this);
        dialogClick.setOnClickListener(this);
        mFlutter.setOnClickListener(this);
        mAnno.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click:
                startActivity(new Intent(MainActivity.this,
                        com.iflytek.voicedemo.MainActivity.class));
                break;
            case R.id.btn_ifly:
                SpeakToTxtActivity.startActivity(this);
                break;
            case R.id.dialogClick:
                showDialog();
                dialogClick.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showDialog();
                    }
                }, 2000);
                break;
            case R.id.mFlutter:
                Intent intent = FlutterActivity.withNewEngine()
                                               .initialRoute("r1")
                                               .build(this);
                startActivity(intent);
                break;
            case R.id.mAnno:
                Intent intent1 = new Intent(MainActivity.this,
                        InjectViewActivity.class);
                intent1.putExtra("name","wxy");
                intent1.putExtra("isBoy",true);
                User user = new User("11",22,true);
                intent1.putExtra("str",new User[]{user});
                List<User> list = new ArrayList<>();
                list.add(user);
                intent1.putExtra("list", (Serializable) list);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    AlertDialog dialog;

    public void showDialog() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this, R.style.dialog).create();
        }
        dialog.show();
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_view, null);
        TextView content = view.findViewById(R.id.content);
        Button cancel = view.findViewById(R.id.cancel);
        ImageView img = view.findViewById(R.id.img);

        Window window = dialog.getWindow();
        window.setContentView(view);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ScreenUtils.ScreenWidth(this) * 15 / 16;
        lp.height = ScreenUtils.ScreenHeight(this);
        window.setAttributes(lp);

        content.setText("随机数" + new Random().nextInt(100));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }

            }
        });
    }

}
