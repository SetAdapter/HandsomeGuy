package com.example.administrator.handsomeguy.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.handsomeguy.MainActivity;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.fragment.childfragment.BoysBookListFragment;
import com.example.administrator.handsomeguy.fragment.childfragment.GirlBookListFragment;
import com.example.administrator.handsomeguy.fragment.childfragment.PublishListFragment;
import com.example.handsomelibrary.base.BaseFragment;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 小说分类 Fragment
 * Created by Stefan on 2018/4/26.
 */

public class BookClassifyFragment extends BaseFragment{
    @BindView(R.id.nts_classify)
    NavigationTabStrip nts_classify;
    @BindView(R.id.vp_classify)
    ViewPager vp_classify;

    String[] titles = {"男生", "女生", "出版"};
    private List<Fragment> mFragments;

    public static BookClassifyFragment newInstance() {
        BookClassifyFragment fragment = new BookClassifyFragment();
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book_classify;
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new BoysBookListFragment());
        mFragments.add(new GirlBookListFragment());
        mFragments.add(new PublishListFragment());

        vp_classify.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
        nts_classify.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0 || positionOffsetPixels == 0) {
                    vp_classify.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                ((MainActivity)getActivity()).setLeftSlide(position==0);
                vp_classify.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_classify.setOffscreenPageLimit(4);
        nts_classify.setTitles(getResources().getStringArray(R.array.book_classify));
        nts_classify.setViewPager(vp_classify);
    }
}
