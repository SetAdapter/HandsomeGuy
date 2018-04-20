package com.example.handsomelibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handsomelibrary.utils.cache.ACache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 * Created by Stefan on 2018/4/20.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected ACache mCache;
    protected BaseActivity mContext;
    protected View mContentView;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (BaseActivity) context;
        mCache = ACache.get(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mContentView==null){
            mContentView = inflater.inflate(getLayoutID(), container, false);
            unbinder= ButterKnife.bind(this,mContentView);
        }
        initView();
        initData();
        return mContentView;
    }

    @Override//Fragment和Activity解除关联的时候调用
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!= null){
            unbinder.unbind();
        }
    }

    //Fragment生命周期管理
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示
            onPause();
        } else {// 重新显示到最前端中
            onResume();
        }
    }
    @Override
    public void onClick(View view) {
    }

    /**
     * 设置布局
     * @return
     */
    protected abstract int getLayoutID();
    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 设置数据
     */
    protected void initData(){}


}
