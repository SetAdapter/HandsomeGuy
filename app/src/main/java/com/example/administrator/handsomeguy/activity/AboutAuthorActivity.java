package com.example.administrator.handsomeguy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.handsomeguy.R;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于作者
 * Created by Stefan on 2018/4/28 14:28.
 */

public class AboutAuthorActivity extends BaseActivity {
    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @Override
    protected int getContentView() {
        return R.layout.activity_about_author;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Glide.with(mContext).load(R.mipmap.img_header)
                .apply(new RequestOptions().transform(new CircleCrop()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                .into(iv_avatar);
    }

    @OnClick({R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                JumpUtils.exitActivity(this);
                break;
        }
    }
}
