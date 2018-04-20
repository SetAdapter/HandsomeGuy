package com.example.administrator.handsomeguy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.T;

import butterknife.BindView;


/**
 * Created by Stefan on 2018/4/20 15:06.
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.text1)
    TextView text;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort("点击了一下");
            }
        });
    }
}
