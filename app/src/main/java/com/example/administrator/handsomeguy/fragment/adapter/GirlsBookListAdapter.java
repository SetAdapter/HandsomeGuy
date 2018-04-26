package com.example.administrator.handsomeguy.fragment.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.handsomeguy.R;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.ClassifyBean;

import java.util.List;

/**
 * Created by Stefan on 2018/4/26.
 */

public class GirlsBookListAdapter extends BaseQuickAdapter<ClassifyBean.DataBean.FemaleBean,BaseViewHolder>{

    public GirlsBookListAdapter(@Nullable List<ClassifyBean.DataBean.FemaleBean> data) {
        super(R.layout.book_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyBean.DataBean.FemaleBean item) {
        helper.setText(R.id.tv_name,item.getName())
                .setText(R.id.tv_count,item.getBookCount()+"本");
        Glide.with(mContext).load(ApiService.WEIYUE_URL+item.getIcon())
                .apply(new RequestOptions().placeholder(R.drawable.vector_default))
                .into((ImageView) helper.getView(R.id.iv_icon));
    }
}
