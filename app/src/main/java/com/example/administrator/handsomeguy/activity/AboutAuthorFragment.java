package com.example.administrator.handsomeguy.activity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.handsomeguy.R;
import com.example.handsomelibrary.base.BaseFragment;

import butterknife.BindView;

/**
 * 关于作者
 * Created by Stefan on 2018/4/28 14:28.
 */

public class AboutAuthorFragment extends BaseFragment {
    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;

    public static AboutAuthorFragment newInstance(){
        AboutAuthorFragment fragment = new AboutAuthorFragment();
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_author;
    }

    @Override
    protected void initData() {
        Glide.with(mContext).load(R.mipmap.img_header)
                .apply(new RequestOptions().transform(new CircleCrop()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                .into(iv_avatar);
    }
}
