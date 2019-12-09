package com.wxy.wxyandroidstudy.highlevel.test1_ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.apkfuns.logutils.LogUtils;
import com.wxy.base.AppConst;
import com.wxy.wxyandroidstudy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author wxy
 * @description:
 * @date :2019-12-04 10:50
 */
public class MyClientActivity extends AppCompatActivity {

    private static class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppConst.MSG_FROM_SERVICE:
                    LogUtils.e("来自服务端的消息："+msg.getData().getString("bb"));
                    break;
                default:
                    break;
            }
        }
    }

    private Messenger clienMessenger = new Messenger(new MyHandle());

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message msg = new Message();
            msg.what = AppConst.MSG_FROM_CLIENT;
            Bundle bundle = new Bundle();
            bundle.putString("aa", "client Hi");
            msg.setData(bundle);
            msg.replyTo = clienMessenger;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        findViewById(R.id.test_tv).setOnClickListener(v -> {
            Intent intent = new Intent(MyClientActivity.this, MyService.class);

            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
