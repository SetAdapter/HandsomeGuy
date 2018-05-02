package com.example.handsomelibrary.api;


import com.example.handsomelibrary.model.BookBean;
import com.example.handsomelibrary.model.BookChaptersBean;
import com.example.handsomelibrary.model.ChapterContentBean;
import com.example.handsomelibrary.model.ClassifyBean;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.utils.FileUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 接口 API
 * Created by Stefan on 2018/4/23.
 */

public interface ApiService {
    String WEIYUE_URL = "http://120.55.57.236/api/";
    String ZHUISHU_IMAGE_URL = "http://statics.zhuishushenqi.com";
    String BOOK_CACHE_PATH = FileUtils.getCachePath() + File.separator
            + "book_cache" + File.separator;

    String FORMAT_BOOK_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    String FORMAT_TIME = "HH:mm";
    String FORMAT_FILE_DATE = "yyyy-MM-dd";


    /**
     * 用户登录
     *
     * @return
     */
    @GET(WEIYUE_URL + "user/login")
    Observable<BaseBean<LoginBean>> login(@Query("name") String username, @Query("password") String password);


    /**
     * 获取书籍信息
     * http://120.55.57.236/books/{bookId}
     *
     * @param bookId
     * @return
     */
    @GET("books/{bookId} ")
    Observable<BaseBean<BookBean>> bookInfo(@Path("bookId") String bookId);

    /**
     * 获取所有分类
     *
     * @return
     */
    @GET("classify")
    Observable<BaseBean<ClassifyBean.DataBean>> bookClassify();

    /**
     * 获取分类下的书籍
     *
     * @param type
     * @param major
     * @param page
     * @return
     */
    @GET("books")
    Observable<BaseBean<List<BookBean>>> bookList(@Query("type") String type,
                                                  @Query("major") String major, @Query("page") int page);


    /**
     * 根据tag获取书籍
     *
     * @param bookTag
     * @param page
     * @return
     */
    @GET("books/tag")
    Observable<BaseBean<List<BookBean>>> booksByTag(@Query("bookTag") String bookTag, @Query("page") int page);

    /**
     * 获取书籍目录
     *
     * @param bookId
     * @return
     */
    @GET("books/{bookId}/chapters")
    Observable<BaseBean<BookChaptersBean>> bookChapters(@Path("bookId") String bookId);


    /**
     * 根据link获取正文
     *link : http://read.qidian.com/chapter/rJgN8tJ_cVdRGoWu-UQg7Q2/6jr-buLIUJSaGfXRMrUjdw2
     * @param link 正文链接
     * @return
     */
    @GET("http://chapterup.zhuishushenqi.com/chapter/{link}")
    Observable<ChapterContentBean> bookContent(@Path("link") String link);

}
