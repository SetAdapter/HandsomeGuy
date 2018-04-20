package com.example.handsomelibrary.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.handsomelibrary.R;

/**
 * Activity 基类
 * Created by Stefan on 2018/4/20 15:07.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 是否显示标题栏
     */
    private  boolean isShowTitle = true;

    /**
     * 是否显示标题栏
     */
    private  boolean isShowState = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isShowTitle){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if(isShowState){
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
        }
        setContentView(getContentView());
        initView();
        initData(savedInstanceState);
    }

    /**
     * 是否设置标题栏
     *
     * @return
     */
    public void setTitle(boolean ishow) {
        isShowTitle=ishow;
    }

    /**
     * 设置是否显示状态栏
     * @param ishow
     */
    public void setState(boolean ishow) {
        isShowState=ishow;
    }

    /**
     * 设置布局
     * @return
     */
    protected abstract int getContentView();
    /**
     * 初始化布局
     */
    protected abstract void initView();
    /**
     * 设置数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 获取TAG的activity名称
     */
    protected final String TAG = this.getClass().getSimpleName();
}