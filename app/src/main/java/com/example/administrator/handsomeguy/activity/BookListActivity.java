package com.example.administrator.handsomeguy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.handsomeguy.MainActivity;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.fragment.HotBookFragment;
import com.example.administrator.handsomeguy.fragment.NewBookFragment;
import com.example.administrator.handsomeguy.fragment.PraiseFragment;
import com.example.handsomelibrary.base.BaseActivity;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookListActivity extends BaseActivity {
    @BindView(R.id.nts_classify)
    NavigationTabStrip nts_classify;
    @BindView(R.id.vp_classify)
    ViewPager vp_classify;

    private List<Fragment> mFragments;
    public static String titleName;

    @Override
    protected int getContentView() {
        return R.layout.activity_book_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        titleName = getIntent().getExtras().getString("titleName");

        mFragments = new ArrayList<>();
        mFragments.add(new HotBookFragment());
        mFragments.add(new NewBookFragment());
        mFragments.add(new PraiseFragment());
        vp_classify.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getStringArray(R.array.book_classify)[position];
            }
        });
        vp_classify.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0 || positionOffsetPixels == 0) {
                    vp_classify.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                vp_classify.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_classify.setOffscreenPageLimit(4);
        nts_classify.setTitles(getResources().getStringArray(R.array.classify_list));
        nts_classify.setViewPager(vp_classify);
    }

    @OnClick({R.id.tv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
