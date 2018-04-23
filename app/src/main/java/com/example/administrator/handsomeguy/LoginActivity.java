package com.example.administrator.handsomeguy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 * Created by Stefan on 2018/4/23 15:36.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.edit_userName)
    EditText edit_userName;
    @BindView(R.id.edit_passWord)
    EditText edit_passWord;
    @BindView(R.id.login)
    TextView login;
    private String userName;
    private String passWord;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    @OnClick({R.id.login})
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login:
                userName = edit_userName.getText().toString();
                passWord = edit_passWord.getText().toString();
                if(userName.equals("")||passWord.equals("")){
                    T.showShort("请输入账号密码！");
                }
        }
    }
}
