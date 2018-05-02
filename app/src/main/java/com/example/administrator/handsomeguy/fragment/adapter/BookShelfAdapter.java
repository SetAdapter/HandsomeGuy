package com.example.administrator.handsomeguy.fragment.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.handsomeguy.R;

import java.util.List;

/**
 * 书架 Adapter
 * Created by Stefan on 2018/5/2.
 */

public class BookShelfAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    public BookShelfAdapter(@Nullable List<Object> data) {
        super(R.layout.item_book_shelf, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
