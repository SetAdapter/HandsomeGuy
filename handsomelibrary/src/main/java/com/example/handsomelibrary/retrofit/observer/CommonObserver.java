package com.example.handsomelibrary.retrofit.observer;


import android.app.Dialog;


import com.example.handsomelibrary.retrofit.RxHttpUtils;

import io.reactivex.disposables.Disposable;

/**
 * 通用的Observer
 * 用户可以根据自己需求自定义自己的类继承BaseObserver<T>即可
 * Created by Stefan on 2018/4/24 9:31.
 */

public abstract class CommonObserver<T> extends BaseObserver<T> {


    private Dialog mProgressDialog;

    public CommonObserver() {
    }

    public CommonObserver(Dialog progressDialog) {
        mProgressDialog = progressDialog;
    }

    /**
     * 成功回调
     *
     * @param t
     */
    protected abstract void onSuccess(T t);

    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void onError(String errorMsg);

    @Override
    public void doOnSubscribe(Disposable d) {
        RxHttpUtils.addDisposable(d);
    }

    @Override
    public void doOnError(String errorMsg) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (!isHideToast()) {
            //ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(T t)    {
        onSuccess(t);
    }

    @Override
    public void doOnCompleted() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
