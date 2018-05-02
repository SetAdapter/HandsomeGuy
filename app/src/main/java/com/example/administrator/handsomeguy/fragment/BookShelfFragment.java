package com.example.administrator.handsomeguy.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.fragment.adapter.BookShelfAdapter;
import com.example.handsomelibrary.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 书架
 * Created by Stefan on 2018/5/2.
 */

public class BookShelfFragment extends BaseFragment{
    @BindView(R.id.rv_book_shelf)
    RecyclerView rv_book_shelf;

    BookShelfAdapter adapter;

    public static BookShelfFragment newInstance(){
        BookShelfFragment fragment = new BookShelfFragment();
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_shelf;
    }

    @Override
    protected void initData() {

        rv_book_shelf.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new BookShelfAdapter(new ArrayList<>());
        rv_book_shelf.setAdapter(adapter);

    }
}
