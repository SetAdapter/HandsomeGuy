package com.example.handsomelibrary.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * activity 生命周期回调
 * Created by Stefan on 2018/4/20.
 */

public class ActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks{
    public static final String TAG = "ActivityCallback";
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "Create()");

        BaseRegisterBean registerBean=new BaseRegisterBean();
        registerBean.setUnbinder(ButterKnife.bind(activity));
        activity.getIntent().putExtra("RegisterBean",registerBean);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "Start()");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "Resume()");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "Pause()");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, "Stop()");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(TAG, "SaveInstanceState()");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "Destroy()");

        //通过序列化获取注册过得ButterKnife 然后注销
        BaseRegisterBean registerBean = (BaseRegisterBean) activity.getIntent().getSerializableExtra("RegisterBean");
        if(registerBean!=null){
            registerBean.getUnbinder().unbind();
        }
    }
}
