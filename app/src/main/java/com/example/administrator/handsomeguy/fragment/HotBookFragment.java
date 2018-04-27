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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 热门小说列表
 * Created by Stefan on 2018/4/26.
 */

public class HotBookFragment extends BaseFragment{
    @BindView(R.id.rv_bookList)
    RecyclerView rv_bookList;

    BookListAdapter adapter;
    private String titleName;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initData() {
        titleName = BookListActivity.titleName;
        adapter=new BookListAdapter(new ArrayList<BookBean>());
        getHotBookList();
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rv_bookList.setLayoutManager(new LinearLayoutManager(mContext));
        rv_bookList.setAdapter(adapter);

    }

    private void getHotBookList() {
        RxHttpUtils.getSingleInstance().addHeaders(tokenMap())
                .createSApi(ApiService.class)
                .bookList("hot",titleName,1)
                .compose(Transformer.<BaseBean<List<BookBean>>>switchSchedulers())
                .subscribe(new CommonObserver<BaseBean<List<BookBean>>>() {
                    @Override
                    protected void onSuccess(BaseBean<List<BookBean>> listBaseBean) {
                        if(listBaseBean!=null&&listBaseBean.getData().size()!=0){
                            adapter.setNewData(listBaseBean.getData());
                        }else {
                            T.showShort("无更多书籍");
                        }

                    }

                    @Override
                    protected void onError(String errorMsg) {

                    }
                });

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
