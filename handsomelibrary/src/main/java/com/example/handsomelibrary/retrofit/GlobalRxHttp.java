package com.example.handsomelibrary.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.example.handsomelibrary.application.BaseApp;
import com.example.handsomelibrary.interceptor.AddCookiesInterceptor;
import com.example.handsomelibrary.interceptor.CacheInterceptor;
import com.example.handsomelibrary.interceptor.HeaderInterceptor;
import com.example.handsomelibrary.interceptor.ReceivedCookiesInterceptor;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * 网络请求工具类---使用的是全局配置的变量
 * Created by Stefan on 2018/4/23.
 */

public class GlobalRxHttp {
    private volatile static GlobalRxHttp instance; // 单例模式

    public static GlobalRxHttp getInstance() {
        if (instance == null) {
            synchronized (GlobalRxHttp.class) {
                if (instance == null) {
                    instance = new GlobalRxHttp();
                }
            }
        }
        return instance;
    }

    /**
     * 设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    public GlobalRxHttp setBaseUrl(String baseUrl) {
        getGlobalRetrofitBuilder().baseUrl(baseUrl);
        return this;
    }

    /**
     * 设置自己的client
     *
     * @param okClient
     * @return
     */
    public GlobalRxHttp setOkClient(OkHttpClient okClient) {
        getGlobalRetrofitBuilder().client(okClient);
        return this;
    }

    /**
     * 添加统一的请求头
     *
     * @param headerMaps
     * @return
     */
    public GlobalRxHttp setHeaders(Map<String, Object> headerMaps) {
        //统一请求头
        headerMaps.put("access-token", SpfUtils.getInstance().getString("token", "weyue"));
        headerMaps.put("app-type", "Android");
        getGlobalOkHttpBuilder().addInterceptor(new HeaderInterceptor(headerMaps));
        return this;
    }

    /**
     * 是否开启请求日志
     *
     * @param isShowLog
     * @return
     */
    public GlobalRxHttp setLog(boolean isShowLog) {
        if (isShowLog) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("拦截返回数据", message);
                    if(message.contains("token无效")){
                        T.showShort("消息失效，请重新登录");
                        try {
                            Class ca=Class.forName("com.example.administrator.handsomeguy.activity.LoginActivity");
                            Context context = BaseApp.getAppContext();
                            Intent intent = new Intent(context, ca);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            context.startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            getGlobalOkHttpBuilder().addInterceptor(loggingInterceptor);
        }
        return this;
    }

    /**
     * 开启缓存，缓存到默认路径
     *
     * @return
     */
    public GlobalRxHttp setCache() {
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        Cache cache = new Cache(new File(Environment.getExternalStorageDirectory().getPath() + "/rxHttpCacheData")
                , 1024 * 1024 * 100);
        getGlobalOkHttpBuilder().addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache);
        return this;
    }

    /**
     * 设置缓存路径及缓存文件大小
     *
     * @param cachePath
     * @param maxSize
     * @return
     */
    public GlobalRxHttp setCache(String cachePath, long maxSize) {
        if (!TextUtils.isEmpty(cachePath) && maxSize > 0) {
            CacheInterceptor cacheInterceptor = new CacheInterceptor();
            Cache cache = new Cache(new File(cachePath), maxSize);
            getGlobalOkHttpBuilder()
                    .addInterceptor(cacheInterceptor)
                    .addNetworkInterceptor(cacheInterceptor)
                    .cache(cache);
        }

        return this;
    }

    /**
     * 持久化保存cookie保存到sp文件中
     *
     * @param saveCookie
     * @return
     */
    public GlobalRxHttp setCookie(boolean saveCookie) {
        if (saveCookie) {
            getGlobalOkHttpBuilder()
                    .addInterceptor(new AddCookiesInterceptor())
                    .addInterceptor(new ReceivedCookiesInterceptor());
        }
        return this;
    }


    /**
     * 设置读取超时时间
     *
     * @param second
     * @return
     */
    public GlobalRxHttp setReadTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置写入超时时间
     *
     * @param second
     * @return
     */
    public GlobalRxHttp setWriteTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置连接超时时间
     *
     * @param second
     * @return
     */
    public GlobalRxHttp setConnectTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 信任所有证书,不安全有风险
     *
     * @return
     */
    //TODO 信任所有证书 待完成
/*    public GlobalRxHttp setSslSocketFactory() {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }*/

    /**
     * 使用预埋证书，校验服务端证书（自签名证书）
     *
     * @param certificates
     * @return
     */
    public GlobalRxHttp setSslSocketFactory(InputStream... certificates) {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(certificates);
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
     *
     * @param bksFile
     * @param password
     * @param certificates
     * @return
     */
    public GlobalRxHttp setSslSocketFactory(InputStream bksFile, String password, InputStream... certificates) {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(bksFile, password, certificates);
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 全局的 retrofit
     *
     * @return
     */
    public static Retrofit getGlobalRetrofit() {
        return RetrofitClient.getInstance().getRetrofit();
    }

    /**
     * 全局的 RetrofitBuilder
     *
     * @return
     */
    public Retrofit.Builder getGlobalRetrofitBuilder() {
        return RetrofitClient.getInstance().getRetrofitBuilder();
    }

    public OkHttpClient.Builder getGlobalOkHttpBuilder() {
        return HttpClient.getInstance().getBuilder();
    }

    /**
     * 使用全局变量的请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public static <K> K createGApi(final Class<K> cls) {
        return getGlobalRetrofit().create(cls);
    }

}
