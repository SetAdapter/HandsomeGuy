package com.example.handsomelibrary.api;


import com.example.handsomelibrary.model.BookBean;
import com.example.handsomelibrary.model.ClassifyBean;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.model.UserBean;
import com.example.handsomelibrary.model.BaseBean;

import java.util.List;

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
    String WEIYUE_URL="http://120.55.57.236/";
    String ZHUISHU_IMAGE_URL = "http://statics.zhuishushenqi.com";
    /**
     * 用户登录
     *
     * @return
     */
    @GET(WEIYUE_URL+"api/user/login")
    Observable<BaseBean<LoginBean>> login(@Query("name") String username, @Query("password") String password);

    /**
     * 获取分类下的书籍
     *
     * @return
     */
    @GET("api/classify")
    Observable<BaseBean<ClassifyBean.DataBean>> bookClassify();

    /**
     * 获取分类下的书籍
     *
     * @param type
     * @param major
     * @param page
     * @return
     */
    @GET("api/books")
    Observable<BaseBean<List<BookBean>>> bookList(@Query("type") String type,
                                                  @Query("major") String major, @Query("page") int page);


}
