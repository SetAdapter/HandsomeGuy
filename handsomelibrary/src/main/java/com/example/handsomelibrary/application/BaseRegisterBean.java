package com.example.handsomelibrary.application;

import java.io.Serializable;

import butterknife.Unbinder;

/**
 * 使用 LifecycleCallback实现给所有 Activity 执行 ButterKnife.bind
 * 屏幕方向监听
 * Created by Stefan on 2018/4/20.
 */

public class BaseRegisterBean implements Serializable{
    Unbinder unbinder;

    public Unbinder getUnbinder() {
        return unbinder;
    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    private BaseOrientoinListener orientoinListener;

    public BaseOrientoinListener getOrientoinListener() {
        return orientoinListener;
    }

    public void setOrientoinListener(BaseOrientoinListener orientoinListener) {
        this.orientoinListener = orientoinListener;
    }
}
