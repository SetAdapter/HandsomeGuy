package com.example.administrator.handsomeguy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.handsomeguy.MainActivity;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.apputils.SharedPreUtils;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;

import java.util.HashMap;
import java.util.Map;

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
                .compose(Transformer.<BaseBean<LoginBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseBean<LoginBean>>(loading_dialog) {
                    @Override
                    protected void onSuccess(BaseBean<LoginBean> loginBean) {
                        if (userName.equals(USERNAME) && passWord.equals(PASSWORD)) {
                            SharedPreUtils.getInstance().putString("token", loginBean.getData().getToken());
                            SharedPreUtils.getInstance().putString("username", loginBean.getData().getName());
                            mCache.put("username",loginBean.getData().getName());
                            JumpUtils.jump(mContext, MainActivity.class, null);
                            finish();
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

    private static final String TAG_EXIT = "exit";

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (intent != null) {
//            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
//            if (isExit) {
//                this.finish();
//            }
//        }
//    }

    private long fristTime = 0;
    private boolean mIsExit;

    /**
     * 双击返回键退出
     */

//    @Override
//    public void onBackPressed() {
//
//        long secondTime = System.currentTimeMillis();
//        if (secondTime - fristTime < 2000) {
//            //finish();
//            Intent intent = new Intent(this,LoginActivity.class);
//            intent.putExtra(LoginActivity.TAG_EXIT, true);
//            startActivity(intent);
//        } else {
//            SnackBarUtils.makeShort(getWindow().getDecorView(), "再点击一次退出应用").show();
//            fristTime = System.currentTimeMillis();
//        }
//
//    }

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
}
