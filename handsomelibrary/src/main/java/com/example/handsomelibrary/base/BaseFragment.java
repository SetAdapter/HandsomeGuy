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

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            lazyData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }

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
    protected  void initView(){}

    /**
     * 设置数据
     */
    protected abstract void initData();

    /**
     * 设置懒加载数据
     */
    protected  void lazyData(){}

}
