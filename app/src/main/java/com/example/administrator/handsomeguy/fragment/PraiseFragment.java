package com.example.administrator.handsomeguy.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.activity.BookListActivity;
import com.example.administrator.handsomeguy.apputils.SharedPreUtils;
import com.example.administrator.handsomeguy.fragment.adapter.BookListAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.BookBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.T;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 好评小说列表
 * Created by Stefan on 2018/4/26.
 */

public class PraiseFragment extends BaseFragment implements OnRefreshLoadmoreListener{
    @BindView(R.id.rv_bookList)
    RecyclerView rv_bookList;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    BookListAdapter adapter;
    private String titleName;
    private int loadPage = 1;
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initData() {
        refresh.setOnRefreshLoadmoreListener(this);
        titleName = BookListActivity.titleName;
        adapter=new BookListAdapter(new ArrayList<BookBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rv_bookList.setLayoutManager(new LinearLayoutManager(mContext));
        rv_bookList.setAdapter(adapter);
        refresh.autoRefresh();
    }

    private void getHotBookList(int loadPage) {
        RxHttpUtils.getSingleInstance().addHeaders(tokenMap())
                .createSApi(ApiService.class)
                .bookList("reputation",titleName,loadPage)
                .compose(Transformer.<BaseBean<List<BookBean>>>switchSchedulers())
                .subscribe(new CommonObserver<BaseBean<List<BookBean>>>() {
                    @Override
                    protected void onSuccess(BaseBean<List<BookBean>> listBaseBean) {
                        if(listBaseBean!=null&&listBaseBean.getData().size()!=0){
                            if(refresh.isRefreshing()){
                                adapter.setNewData(listBaseBean.getData());
                                refresh.finishRefresh();
                            }else if(refresh.isLoading()){
                                adapter.addData(listBaseBean.getData());
                                refresh.finishLoadmore();
                            }else {
                                adapter.setNewData(listBaseBean.getData());
                            }
                        }else {
                            T.showShort("无更多书籍");
                        }

                    }

                    @Override
                    protected void onError(String errorMsg) {
                        refresh.finishRefresh();
                        refresh.finishLoadmore();
                    }
                });

    }

    @Override
    public void onPause() {
        super.onPause();
        if (refresh.isRefreshing()) {
            refresh.finishRefresh();
        }
        if (refresh.isLoading()) {
            refresh.finishLoadmore();
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

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        loadPage++;
        getHotBookList(loadPage);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        loadPage=1;
        getHotBookList(loadPage);
    }
}
