package com.example.handsomelibrary.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.example.handsomelibrary.retrofit.RxHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础 application
 * Created by Stefan on 2018/4/20.
 */

public class BaseApp extends Application {

    private static BaseApp mApplication; // 单例模式

    /**
     * 单例模式，获取Application的实例
     *
     * @return
     */
    public static BaseApp getApplication() {
        return mApplication;
    }

    public static Context getAppContext() {
        return mApplication;
    }

    public static Resources getAppResources() {
        return mApplication.getResources();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Map<String, Object> headerMaps = new HashMap<>();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallback());

        /**
         * 初始化配置
         */
        RxHttpUtils.init(this);

        RxHttpUtils.getInstance()
                //开启全局配置
                .config()
                //全局的BaseUrl
                //.setBaseUrl("")
                //.setHeaders(headerMaps)
                //全局持久话cookie,保存本地每次都会携带在header中
                .setCookie(false)
                //全局ssl证书认证
                //信任所有证书,不安全有风险
                .setSslSocketFactory()
                //使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.cer"))
                //使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.bks"), "123456", getAssets().open("your.cer"))
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
//                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setLog(true)
                ;
    }


}
