package com.example.administrator.handsomeguy.fragment.childfragment;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.fragment.adapter.BoysBookListAdapter;
import com.example.administrator.handsomeguy.fragment.adapter.GirlsBookListAdapter;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.model.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 女生分类列表
 * Created by Stefan on 2018/4/26 13:47.
 */

public class GirlBookListFragment extends BaseFragment{

    @BindView(R.id.rv_bookList)
    RecyclerView rv_bookList;
    GirlsBookListAdapter adapter;
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initData() {
        ClassifyBean.DataBean classifyBean = (ClassifyBean.DataBean) mCache.getAsObject("classifyBean");
        if(classifyBean!=null){
            adapter = new GirlsBookListAdapter(classifyBean.getFemale());
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            rv_bookList.setLayoutManager(new LinearLayoutManager(mContext));
            rv_bookList.setAdapter(adapter);
        }
    }
}