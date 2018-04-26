package com.example.handsomelibrary.api;


import com.example.handsomelibrary.model.ClassifyBean;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.model.UserBean;
import com.example.handsomelibrary.model.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口 API
 * Created by Stefan on 2018/4/23.
 */

public interface ApiService {
     String LOGIN_URL="http://www.wanandroid.com/";
    String WEIYUE_URL="http://120.55.57.236/";
    /**
     * 用户注册
     *
     * @return
     */
    @POST(LOGIN_URL+"user/login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 获取所有分类
     *
     * @return
     */
    @GET("api/classify")
    Observable<BaseBean<ClassifyBean.DataBean>> bookClassify();

}
