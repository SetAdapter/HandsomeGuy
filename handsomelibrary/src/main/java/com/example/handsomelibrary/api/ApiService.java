package com.example.handsomelibrary.api;


import com.example.handsomelibrary.model.UserBean;
import com.example.handsomelibrary.model.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口 API
 * Created by Stefan on 2018/4/23.
 */

public interface ApiService {
     String BASE_URL="";

    /**
     * 用户注册
     *
     * @return
     */
    @POST(ModelPath.USER + "/register")
    Observable<BaseBean<String>> register(@Query("name") String username, @Query("password") String password);

    /**
     * 用户登录
     *
     * @return
     */
    @GET(ModelPath.USER + "/login")
    Observable<BaseBean<UserBean>> login(@Query("name") String username, @Query("password") String password);
}
