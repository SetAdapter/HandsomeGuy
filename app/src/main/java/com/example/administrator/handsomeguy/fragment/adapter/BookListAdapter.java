package com.example.administrator.handsomeguy.fragment.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.apputils.BaseUtils;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.BookBean;

import java.util.List;

/**
 * 热门/新书/好评 小说列表 Adapter
 * Created by Stefan on 2018/4/27.
 */

public class BookListAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {
    public BookListAdapter(@Nullable List<BookBean> data) {
        super(R.layout.hot_book_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
        String wordCount = item.getLatelyFollower() / 10000 > 0 ? BaseUtils.format1Digits((double) item.getLatelyFollower() / 10000) + "万" : item.getLatelyFollower() + "";
        helper.setText(R.id.tv_bookName, item.getTitle())
                .setText(R.id.tv_author_type, item.getAuthor() + "  |  " + item.getMajorCate())
                .setText(R.id.tv_brief, item.getLongIntro())
                .setText(R.id.ctv_arrow_count, wordCount)
                .setText(R.id.ctv_retention, item.getRetentionRatio() + "%");
        Glide.with(mContext).load(ApiService.ZHUISHU_IMAGE_URL+item.getCover())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_book_loading))
                .into((ImageView) helper.getView(R.id.iv_bookImg));
    }
}
