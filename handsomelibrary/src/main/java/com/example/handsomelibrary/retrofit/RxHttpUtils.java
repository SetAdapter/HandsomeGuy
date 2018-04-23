package com.example.handsomelibrary.retrofit;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 网络请求
 * Created by Stefan on 2018/4/23.
 */

public class RxHttpUtils {
    private static RxHttpUtils instance=null;
    private static Application mContext;

    private static List<Disposable> disposables;

    //单例模式 双重锁
    private static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                    disposables=new ArrayList<>();
                }
            }
        }
        return instance;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     * @param app Application
     */
    public static void init(Application app) {
        mContext = app;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return mContext;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (mContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.init() 初始化！");
        }
    }


}
