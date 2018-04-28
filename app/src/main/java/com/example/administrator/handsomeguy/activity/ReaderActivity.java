package com.example.administrator.handsomeguy.activity;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.handsomeguy.R;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;

import butterknife.OnClick;

/**
 * 小说阅读界面
 * Created by Stefan on 2018/4/28 17:31.
 */
public class ReaderActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_reader;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_toolbar_title})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_toolbar_title:
                JumpUtils.exitActivity(mContext);
                break;
        }
    }

}
