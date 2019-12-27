package com.wxy.wxyandroidstudy.highlevel.test1_ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.apkfuns.logutils.LogUtils;
import com.wxy.base.AppConst;

import androidx.annotation.Nullable;

/**
 * @author wxy
 * @description:
 * @date :2019-12-04 10:18
 */
public class MyService extends Service {

    private static class MyServiceHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppConst.MSG_FROM_CLIENT:
                    LogUtils.e("收到客户端发来的消息："+msg.getData().getString("aa"));
                    Messenger messenger = msg.replyTo;
                    Message message = new Message();
                    message.what = AppConst.MSG_FROM_SERVICE;
                    Bundle bundle = new Bundle();
                    bundle.putString("bb","嗨 client 我收到消息了");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private Messenger messenger = new Messenger(new MyServiceHandle());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    class Myrh extends Thread{
        @Override
        public void run() {
            super.run();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
