package com.example.handsomelibrary.utils;

import android.widget.Toast;

import com.example.handsomelibrary.application.BaseApplication;

/**
 * Toast统一管理类 (解决多次弹出toast)
 * Created by fangs on 2017/3/1.
 */
public class T {

    /** 显示toast 开关 */
    public static boolean isShow = true;
    private static Toast toast;

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        show(message.toString(), Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        show(BaseApplication.getApplication().getResources().getString(message), Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        show(message.toString(), Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        show(BaseApplication.getApplication().getResources().getString(message), Toast.LENGTH_LONG);
    }

    /**
     * 显示系统 toast
     * @param message 消息
     */
    private static void show(String message, int duration){
        if (isShow){

            if (null == toast){
                toast =  Toast.makeText(BaseApplication.getApplication(), message, duration);
            } else {
                toast.setText(message);
            }

            toast.setDuration(duration);
            toast.show();
        }
    }
}
