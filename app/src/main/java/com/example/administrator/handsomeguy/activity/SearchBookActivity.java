package com.example.administrator.handsomeguy.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.apputils.SharedPreUtils;
import com.example.administrator.handsomeguy.fragment.adapter.BookListAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BookBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 小说搜索
 * Created by Stefan on 2018/5/31 16:36.
 */
public class SearchBookActivity extends BaseActivity {
    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.rv_search)
    RecyclerView rv_search;

    BookListAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_book;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        rv_search.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new BookListAdapter(new ArrayList<>());
        rv_search.setAdapter(adapter);

        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    getSearchBooks(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BookBean item = (BookBean)adapter.getData().get(position);
                Intent intent=new Intent();
                intent.setClass(mContext, BookDetailActivity.class);
                intent.putExtra("bookid", item.get_id());
                if (android.os.Build.VERSION.SDK_INT > 20) {
                    ImageView imageView = view.findViewById(R.id.iv_bookImg);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext, imageView, "bookImage").toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });


    }

    private void getSearchBooks(String text){
        RxHttpUtils.getSingleInstance()
                .addHeaders(tokenMap())
                .createSApi(ApiService.class)
                .searchBooks(text)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<BookBean>>() {
                    @Override
                    protected void onSuccess(List<BookBean> bookBeans) {
                        if(bookBeans!=null){
                            adapter.setNewData(bookBeans);
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {

                    }
                });
    }

    @OnClick({R.id.tv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_back:
                JumpUtils.exitActivity(this);
                break;
        }
    }

    /**
     * 获取token
     *
     * @return
     */
    public Map tokenMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("access-token", SharedPreUtils.getInstance().getString("token", "muyin"));
        map.put("app-type", "Android");
//        map.put("version-code", WYApplication.packageInfo.versionCode);
        return map;
    }

}
