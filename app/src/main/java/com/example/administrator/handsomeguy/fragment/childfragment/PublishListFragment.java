package com.example.administrator.handsomeguy.fragment.childfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.fragment.adapter.GirlsBookListAdapter;
import com.example.administrator.handsomeguy.fragment.adapter.PublishListAdapter;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.model.ClassifyBean;

import butterknife.BindView;

/**
 * Created by Stefan on 2018/4/26.
 */

public class PublishListFragment extends BaseFragment{

    @BindView(R.id.rv_bookList)
    RecyclerView rv_bookList;
    PublishListAdapter adapter;
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initData() {
        ClassifyBean.DataBean classifyBean = (ClassifyBean.DataBean) mCache.getAsObject("classifyBean");
        if(classifyBean!=null){
            adapter = new PublishListAdapter(classifyBean.getPress());
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            rv_bookList.setLayoutManager(new LinearLayoutManager(mContext));
            rv_bookList.setAdapter(adapter);
        }
    }
}
