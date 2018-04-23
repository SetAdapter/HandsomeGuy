package com.example.handsomelibrary.retrofit;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.handsomelibrary.constant.SPKeys;
import com.example.handsomelibrary.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 网络请求
 * Created by Stefan on 2018/4/23.
 */

public class RxHttpUtils {

    private static RxHttpUtils instance;
    private static Application mContext;

    private static List<Disposable> disposables;

    //单例模式 双重锁
    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                    disposables = new ArrayList<>();
                }
            }
        }
        return instance;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
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

    public GlobalRxHttp config() {
        return GlobalRxHttp.getInstance();
    }

    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return GlobalRxHttp.createGApi(cls);
    }

    /**
     * 获取单个请求配置实例
     *
     * @return SingleRxHttp
     */
    public static SingleRxHttp getSingleInstance() {
        return SingleRxHttp.getInstance();
    }

    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    //TODO 下载文件 待完成
/*    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }*/

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    //TODO 上传图片 待完成
   /* public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadRetrofit.uploadImg(uploadUrl, filePath);
    }*/

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    //TODO 上传多张图片 待完成
    /* public static Observable<ResponseBody> uploadImgs(String uploadUrl, List<String> filePaths) {
        return UploadRetrofit.uploadImgs(uploadUrl, filePaths);
    }*/

    /**
     * 获取Cookie
     *
     * @return HashSet
     */
    public static HashSet<String> getCookie() {
        HashSet<String> preferences = (HashSet<String>) SPUtils.get(SPKeys.COOKIE, new HashSet<String>());
        return preferences;
    }

    /**
     * 获取disposable 在onDestroy方法中取消订阅disposable.dispose()
     *
     * @param disposable disposable
     */
    public static void addDisposable(Disposable disposable) {
        if (disposables != null) {
            disposables.add(disposable);
        }
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest() {
        if (disposables != null) {
            for (Disposable disposable : disposables) {
                disposable.dispose();
            }
            disposables.clear();
        }
    }

    /**
     * 取消单个请求
     */
    public static void cancelSingleRequest(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
