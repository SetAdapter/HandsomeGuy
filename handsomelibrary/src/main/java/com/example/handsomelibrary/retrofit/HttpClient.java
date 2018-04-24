package com.example.handsomelibrary.retrofit;

import okhttp3.OkHttpClient;

/**
 * okHttp client
 * Created by Stefan on 2018/4/23.
 */

public class HttpClient {
    private static HttpClient instance;
    private OkHttpClient.Builder builder;

    public HttpClient() {
        builder = new OkHttpClient.Builder();
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }
}
