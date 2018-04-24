package com.example.administrator.handsomeguy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                userName = edit_userName.getText().toString();
                passWord = edit_passWord.getText().toString();
//                if(userName.equals("")||passWord.equals("")){
//                    T.showShort("请输入账号密码！");
//                }
                getLogin(userName, passWord);

        }
    }

    private void getLogin(final String userName, final String passWord) {
        RxHttpUtils.getSingleInstance()
                .addHeaders(tokenMap())
                .createSApi(ApiService.class)
                .login(userName, passWord)
                .compose(Transformer.<BaseBean<LoginBean>>switchSchedulers())
                .subscribe(new CommonObserver<BaseBean<LoginBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<LoginBean> stringBaseBean) {
                        if (userName.equals(USERNAME) && passWord.equals(PASSWORD)) {
                            T.showShort("OK!!!");
                        } else {
                            T.showShort("请输入正确的账号密码");
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                    }
                });
    }
}
