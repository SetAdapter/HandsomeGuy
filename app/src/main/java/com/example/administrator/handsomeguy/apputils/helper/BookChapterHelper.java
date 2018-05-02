package com.example.administrator.handsomeguy.apputils.helper;


import com.example.handsomelibrary.model.BookChapterBean;
import com.example.handsomelibrary.model.gen.BookChapterBeanDao;
import com.example.handsomelibrary.model.gen.DaoSession;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Liang_Lu on 2017/12/1.
 * 书籍目录数据库操作
 */

public class BookChapterHelper {
    private static volatile BookChapterHelper sInstance;
    private static DaoSession daoSession;
    private static BookChapterBeanDao bookChapterBeanDao;

    public static BookChapterHelper getsInstance() {
        if (sInstance == null) {
            synchronized (BookChapterHelper.class) {
                if (sInstance == null) {
                    sInstance = new BookChapterHelper();
                    daoSession = DaoDbHelper.getInstance().getSession();
                    bookChapterBeanDao = daoSession.getBookChapterBeanDao();
                }
            }
        }
        return sInstance;
    }

    /**
     * 保存书籍目录   异步
     *
     * @param bookChapterBeans
     */
    public void saveBookChaptersWithAsync(List<BookChapterBean> bookChapterBeans) {
        daoSession.startAsyncSession()
                .runInTx(
                        new Runnable() {
                            @Override
                            public void run() {
                                //存储BookChapterBeans
                                daoSession.getBookChapterBeanDao()
                                        .insertOrReplaceInTx(bookChapterBeans);
                            }
                        }
                );
    }

    /**
     * 删除书籍目录
     *
     * @param bookId 书籍id
     */
    public void removeBookChapters(String bookId) {
        bookChapterBeanDao.queryBuilder().where(BookChapterBeanDao.Properties.BookId.eq(bookId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    /**
     * 异步RX查询 书籍目录
     */
    public Observable<List<BookChapterBean>> findBookChaptersInRx(String bookId) {
        return Observable.create(new ObservableOnSubscribe<List<BookChapterBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BookChapterBean>> e) throws Exception {
                List<BookChapterBean> chapterBeans = daoSession.getBookChapterBeanDao()
                        .queryBuilder()
                        .where(BookChapterBeanDao.Properties.BookId.eq(bookId))
                        .list();
                e.onNext(chapterBeans);
            }
        });
    }

}
