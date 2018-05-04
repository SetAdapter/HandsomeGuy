package com.example.administrator.handsomeguy.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.apputils.BaseUtils;
import com.example.administrator.handsomeguy.apputils.SharedPreUtils;
import com.example.administrator.handsomeguy.apputils.apputils.CollBookHelper;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BookBean;
import com.example.handsomelibrary.model.gen.CollBookBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.view.theme.ColorRelativeLayout;
import com.example.handsomelibrary.view.theme.ColorTextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity {
    @BindView(R.id.iv_book_image)
    ImageView iv_book_image;
    @BindView(R.id.tv_book_name)
    TextView tv_book_name;
    @BindView(R.id.ctv_book_author)
    ColorTextView mCtvBookAuthor;
    @BindView(R.id.tv_book_classify)
    TextView mTvBookClassify;
    @BindView(R.id.tv_word_updatetime)
    TextView mTvWordUpdatetime;
    @BindView(R.id.ctv_score)
    ColorTextView mCtvScore;
    @BindView(R.id.tv_fow_num)
    TextView mTvFowNum;
    @BindView(R.id.tv_good_num)
    TextView mTvGoodNum;
    @BindView(R.id.tv_word_count)
    TextView mTvWordCount;
    @BindView(R.id.tv_book_brief)
    TextView mTvBookBrief;
    @BindView(R.id.ll_fow)
    LinearLayout mLlFow;
    @BindView(R.id.crl_start_read)
    ColorRelativeLayout mCrlStartRead;
    @BindView(R.id.ctv_addbook)
    TextView mCtvAddbook;
    @BindView(R.id.tv_evaluate)
    TextView mTvEvaluate;
    @BindView(R.id.fl_tags)
    TagFlowLayout mFlTags;
    @BindView(R.id.ll_tag)
    LinearLayout mLlTag;
    @BindView(R.id.tv_copyright)
    TextView mTvCopyRight;
    @BindView(R.id.tv_read)
    TextView mTvRead;

    private String bookid;
    private CollBookBean mCollBookBean;
    private BookBean bookBean;
//    IBookData bookData;
//    public interface IBookData{
//        void getBookInfo(BookBean bookBean);
//    }


    @Override
    protected int getContentView() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        bookid = extras.getString("bookid");
        if (bookid != null && !bookid.equals("")) {
            getBookInfo(bookid);
        }
    }

    /**
     * 获取书籍信息
     *
     * @param bookid
     */
    public void getBookInfo(String bookid) {
        RxHttpUtils.getSingleInstance()
                .addHeaders(tokenMap())
                .createSApi(ApiService.class)
                .bookInfo(bookid)
                .compose(Transformer.<BookBean>switchSchedulers())
                .subscribe(new CommonObserver<BookBean>() {
                    @Override
                    protected void onSuccess(BookBean bookBean) {
                        setBookInfo(bookBean);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        L.e(errorMsg);
                    }
                });
    }

    private void setBookInfo(BookBean bookBean) {
        Glide.with(mContext)
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.ic_book_loading))
                .load(ApiService.ZHUISHU_IMAGE_URL + bookBean.getCover())
                .into(iv_book_image);
        tv_book_name.setText(bookBean.getTitle());//书名
        mCtvBookAuthor.setText(bookBean.getAuthor());//作者
        mTvBookClassify.setText("  |  " + bookBean.getMajorCate());
        mTvWordCount.setText(bookBean.getSerializeWordCount() + "");
        mTvFowNum.setText(bookBean.getLatelyFollower() + "");
        mTvGoodNum.setText(bookBean.getRetentionRatio() + "%");
        mTvBookBrief.setText(bookBean.getLongIntro());
        if (bookBean.getTags().size() > 0) {
            mLlTag.setVisibility(View.VISIBLE);
            mFlTags.setAdapter(new TagAdapter<String>(bookBean.getTags()) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tags_tv, mFlTags, false);
                    tv.setText(s);
                    return tv;
                }
            });
//        mFlTags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                String tag = bookBean.getTags().get(position);
//                showTagDialog(tag);
//                return true;
//            }
//        });
        } else {
            mLlTag.setVisibility(View.GONE);
            mFlTags.setVisibility(View.GONE);
        }
        if (bookBean.getRating() != null) {
            mCtvScore.setText(BaseUtils.format1Digits(bookBean.getRating().getScore()));
            mTvEvaluate.setText(bookBean.getRating().getCount() + "人评");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        String wordCount = bookBean.getWordCount() / 10000 > 0 ? bookBean.getWordCount() / 10000 + "万字" : bookBean.getWordCount() + "字";
        try {
            Date d = format.parse(bookBean.getUpdated().replace("Z", " UTC"));//注意是空格+UTC
            Date nowDate = new Date();
            int day = (int) ((nowDate.getTime() - d.getTime()) / (1000 * 3600 * 24));
            int hour = (int) ((nowDate.getTime() - d.getTime()) / (1000 * 3600));
            String time = day > 0 ? day + "天前" : hour + "小时前";
            mTvWordUpdatetime.setText(wordCount + "  |  " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //设置书籍
        mCollBookBean = CollBookHelper.getsInstance().findBookById(bookBean.get_id());
        if (bookBean.isIsCollect()) {
            mCtvAddbook.setText("移除书架");
        } else {
            mCtvAddbook.setText("加入书架");
        }

        if (mCollBookBean == null) {
            mCollBookBean = bookBean.getCollBookBean();
        }
    }

    /**
     * 获取token
     *
     * @return
     */
    public Map tokenMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("access-token", SharedPreUtils.getInstance().getString("token", "weyue"));
        map.put("app-type", "Android");
//        map.put("version-code", WYApplication.packageInfo.versionCode);
        return map;
    }

    @OnClick({R.id.tv_back, R.id.crl_start_read})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                JumpUtils.exitActivity(this);
                break;
            case R.id.crl_start_read:
                Bundle bundle = new Bundle();
                bundle.putSerializable(ReaderActivity.EXTRA_COLL_BOOK, mCollBookBean);
                bundle.putBoolean(ReaderActivity.EXTRA_IS_COLLECTED, false);
                JumpUtils.jump(mContext,ReaderActivity.class,bundle);
                break;
        }
    }
}
