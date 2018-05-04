package com.example.administrator.handsomeguy.fragment.childfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.activity.BookListActivity;
import com.example.administrator.handsomeguy.fragment.adapter.BoysBookListAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.ClassifyBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 男生分类列表
 * Created by Stefan on 2018/4/26 13:47.
 */

public class BoysBookListFragment extends BaseFragment {
    @BindView(R.id.rv_bookList)
    RecyclerView rv_bookList;

    BoysBookListAdapter adapter;
    private Dialog loading_dialog;
    private List<ClassifyBean.DataBean.MaleBean> beanList = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initData() {
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("loading...").create();
        getClassify();
        adapter = new BoysBookListAdapter(new ArrayList<ClassifyBean.DataBean.MaleBean>());
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

    private void getClassify() {
        RxHttpUtils.createApi(ApiService.class)
                .bookClassify()
                .compose(Transformer.<BaseBean<ClassifyBean.DataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<BaseBean<ClassifyBean.DataBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<ClassifyBean.DataBean> classifyBean) {
                        loading_dialog.dismiss();
                        if(null!=classifyBean){
                            adapter.setNewData(classifyBean.getData().getMale());
                            mCache.put("classifyBean",classifyBean.getData());
                            beanList=classifyBean.getData().getMale();
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        loading_dialog.dismiss();
                    }
                });

    }

}
