package com.example.handsomelibrary.application;

import android.app.Application;

/**
 * Created by Stefan on 2018/4/20.
 */

public class BaseApplication extends Application {

    private static BaseApplication mApplication; // 单例模式


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    /**
     * 单例模式，获取Application的实例
     * @return
     */
    public static BaseApplication getApplication() {
        return mApplication;
    }
}
