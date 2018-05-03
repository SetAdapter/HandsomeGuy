package com.example.administrator.handsomeguy.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.apputils.BaseUtils;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 意见反馈
 * Created by Stefan on 2018/5/3.
 */

public class FeedBackFragment extends BaseFragment {
    @BindView(R.id.edit_qq)
    EditText edit_qq;
    @BindView(R.id.edit_content)
    EditText edit_content;

    public static FeedBackFragment newInstance(){
        FeedBackFragment fragment = new FeedBackFragment();
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected void initData() {
    }

    private void getFeedBack(String qq,String content) {
        RxHttpUtils.createApi(ApiService.class)
                .getFeedBack(qq,content)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<String>() {
                    @Override
                    protected void onSuccess(String s) {
                        edit_qq.setText("");
                        edit_content.setText("");
                        T.showShort("提交反馈成功");
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                    }
                });

    }

    @OnClick({R.id.bt_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_commit:
                String qq = edit_qq.getText().toString();
                String content = edit_content.getText().toString();
                if(TextUtils.isEmpty(qq)){
                    T.showShort("请输入QQ号");
                    return;
                }
                if(TextUtils.isEmpty(content)){
                    T.showShort("请描述一下好的建议");
                    return;
                }
                getFeedBack(qq,content);

                BaseUtils.hideInput(edit_content);
                break;
        }
    }

}
