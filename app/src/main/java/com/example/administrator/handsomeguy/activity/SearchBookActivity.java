package com.example.administrator.handsomeguy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.handsomeguy.R;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 小说搜索
 * Created by Stefan on 2018/5/31 16:36.
 */
public class SearchBookActivity extends BaseActivity {
    @BindView(R.id.edit_search)
    EditText edit_search;
    @Override
    protected int getContentView() {
        return R.layout.activity_search_book;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_back:
                JumpUtils.exitActivity(this);
                break;
        }
    }

}
