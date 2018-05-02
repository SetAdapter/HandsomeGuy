package com.example.administrator.handsomeguy.apputils;

import android.content.Intent;

import com.example.administrator.handsomeguy.activity.LoginActivity;
import com.example.administrator.handsomeguy.apputils.helper.LoadingHelper;
import com.example.handsomelibrary.application.BaseApp;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.BaseDataObserver;

import io.reactivex.disposables.Disposable;

/**
 * Created by Liang_Lu on 2017/12/4.
 */

public abstract class RxObserver<T> extends BaseDataObserver<T> {

    boolean isLoading;

    public RxObserver() {
    }

    public RxObserver(boolean isLoading) {
        this.isLoading = isLoading;
    }

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param data 结果
     */
    protected abstract void onSuccess(T data);

    /**
     * //     * 成功回调
     * //     *
     * //     * @param d 结果
     * //
     */
//    public abstract void onSubscribe(Disposable d);
    @Override
    public void doOnSubscribe(Disposable d) {
//        onSubscribe(d);
        RxHttpUtils.addDisposable(d);
    }

    @Override
    public void doOnError(String errorMsg) {
        dismissLoading();
        com.example.handsomelibrary.utils.T.showShort(errorMsg);
        onError(errorMsg);
    }


    @Override
    public void doOnNext(BaseBean<T> data) {
        //可以根据需求对code统一处理
        switch (data.getCode()) {
            case 10000:
                onSuccess(data.getData());
                break;
            case 60001:
            case 60002:
                com.example.handsomelibrary.utils.T.showShort(data.getMsg());
                Intent intent = new Intent(BaseApp.getAppContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApp.getAppContext().startActivity(intent);
                break;
            case 10005:
            case 40000:
            case 40001:
            case 40003:
            case 40004:
            case 40005:
            case 50000:
            case 50003:
                com.example.handsomelibrary.utils.T.showShort(data.getMsg());
                onError(data.getMsg());
                break;
            default:
                onError(data.getMsg());
                break;
        }
    }

    @Override
    public void doOnCompleted() {
        dismissLoading();
    }

    /**
     * 隐藏loading对话框
     */
    private void dismissLoading() {
        if (isLoading) {
            LoadingHelper.getInstance().hideLoading();
        }
    }


}
