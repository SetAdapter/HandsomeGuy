package com.example.handsomelibrary.retrofit.observer;

import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.retrofit.interfaces.IDataSubscriber;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 基类BaseObserver使用BaseBean
 * Created by Stefan on 2018/4/24 9:36.
 */

public abstract class BaseDataObserver<T> implements Observer<BaseBean<T>>, IDataSubscriber<T> {

    /**
     * 是否隐藏toast
     *
     * @return
     */
    protected boolean isHideToast() {
        return false;
    }

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(BaseBean<T> baseData) {
        doOnNext(baseData);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }


    private void setError(String errorMsg) {
        doOnError(errorMsg);
    }

}
