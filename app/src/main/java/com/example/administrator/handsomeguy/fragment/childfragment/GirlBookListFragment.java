package com.example.administrator.handsomeguy.fragment.childfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.activity.BookListActivity;
import com.example.administrator.handsomeguy.fragment.adapter.GirlsBookListAdapter;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.model.ClassifyBean;
import com.example.handsomelibrary.utils.JumpUtils;

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

    private List<ClassifyBean.DataBean.FemaleBean> beanList = new ArrayList<>();
    GirlsBookListAdapter adapter;
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initData() {
        ClassifyBean.DataBean classifyBean = (ClassifyBean.DataBean) mCache.getAsObject("classifyBean");
        if(classifyBean!=null){
            beanList=classifyBean.getFemale();
            adapter = new GirlsBookListAdapter(classifyBean.getFemale());
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            rv_bookList.setLayoutManager(new LinearLayoutManager(mContext));
            rv_bookList.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle=new Bundle();
                    bundle.putString("titleName",beanList.get(position).getName());
                    JumpUtils.jump(mContext, BookListActivity.class,bundle);
                }
            });


        }
    }
}
