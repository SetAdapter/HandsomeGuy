package com.example.handsomelibrary.interceptor;


import android.app.Dialog;

import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.retrofit.observer.ApiException;
import com.example.handsomelibrary.utils.L;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 控制操作线程的辅助类
 * Created by Stefan on 2018/4/23 14:30.
 */

public class Transformer {

    /**
     * 无参数
     *
     * @param <T> 泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> switchSchedulers() {

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull BaseBean<T> tBaseBean) throws Exception {
                        if (tBaseBean.isSuccess()) {
                            return createData(tBaseBean.getData());
                        } else {
                            return Observable.error(new ApiException(tBaseBean.getMsg(), tBaseBean.getCode()));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());


            }
        };
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .doOnSubscribe(new Consumer<Disposable>() {
//                            @Override
//                            public void accept(@NonNull Disposable disposable) throws Exception {
//
//                            }
//                        })
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
    }

    /**
     * 带参数  显示loading对话框
     *
     * @param dialog loading
     * @param <T>    泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<T, T> switchSchedulers(final Dialog dialog) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                if (dialog != null) {
                                    dialog.show();
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                try {
                    L.e("net", "成功 _ onNext");
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    L.e("net", "异常 _ onError");
                    subscriber.onError(e);
                }
            }
        });
    }
}
