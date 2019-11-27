package com.wxy.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author wxy
 * @description:
 * @date :2019-11-15 14:11
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
