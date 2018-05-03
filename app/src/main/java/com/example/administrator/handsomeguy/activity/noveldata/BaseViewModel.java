package com.example.administrator.handsomeguy.activity.noveldata;

import android.content.Context;

import com.example.administrator.handsomeguy.apputils.SharedPreUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;


/**
 * Created by Stefan on 2018/5/2.
 */

public class BaseViewModel {
    protected Context mContext;
    private List<Disposable> disposables = new ArrayList<>();

    public BaseViewModel(Context mContext) {
        this.mContext = mContext;
    }


    public void addDisposadle(Disposable disposable) {
        disposables.add(disposable);
    }

    /**
     * 获取token
     *
     * @return
     */
    public Map tokenMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("access-token", SharedPreUtils.getInstance().getString("token", "weyue"));
        map.put("app-type", "Android");
//        map.put("version-code", WYApplication.packageInfo.versionCode);
        return map;
    }

    public void onDestroy() {
        if (disposables.size() > 0) {
            for (Disposable disposable : disposables) {
                disposable.dispose();
            }
            disposables.clear();
        }
    }


}
