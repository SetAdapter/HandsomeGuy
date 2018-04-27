package com.example.administrator.handsomeguy;

import android.animation.Animator;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.handsomeguy.activity.LoginActivity;
import com.example.administrator.handsomeguy.adapter.MainMenuAdapter;
import com.example.administrator.handsomeguy.apputils.BaseUtils;
import com.example.administrator.handsomeguy.apputils.SharedPreUtils;
import com.example.administrator.handsomeguy.apputils.SnackBarUtils;
import com.example.administrator.handsomeguy.apputils.Theme;
import com.example.administrator.handsomeguy.apputils.ThemeUtils;
import com.example.administrator.handsomeguy.fragment.BookClassifyFragment;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.MainMenuBean;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.view.ResideLayout;
import com.example.handsomelibrary.view.theme.ColorRelativeLayout;
import com.example.handsomelibrary.view.theme.ColorUiUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 首页
 * Created by Stefan on 2018/4/20 15:06.
 */
public class MainActivity extends BaseActivity implements ColorChooserDialog.ColorCallback{
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.top_menu)
    LinearLayout mTopMenu;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tv_theme)
    TextView mTvTheme;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.bottom_menu)
    LinearLayout mBottomMenu;
    @BindView(R.id.menu)
    ColorRelativeLayout mMenu;
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.resideLayout)
    ResideLayout mResideLayout;
    @BindView(R.id.iv_toolbar_more)
    AppCompatImageView mIvToolBarMore;

    private long fristTime = 0;
    private MainMenuAdapter mainMenuAdapter;
    private FragmentManager fragmentManager;
    private String currentFragmentTag;
    private List<MainMenuBean> menuBeans = new ArrayList<>();


    @Override
    protected int getContentView()   {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        setState(true);
//        setTitle(true);
        fragmentManager = getSupportFragmentManager();
        initMenu();
        switchFragment("分类");
    }

    private void initMenu() {
        mTvDesc.setSelected(true);
        BaseUtils.setIconDrawable(mTvSetting, R.drawable.vector_setting);
        BaseUtils.setIconDrawable(mTvTheme, R.drawable.vector_theme);

        getMenuData();
        mRvMenu.setLayoutManager(new LinearLayoutManager(mContext));
        mainMenuAdapter = new MainMenuAdapter(menuBeans);
        mRvMenu.setAdapter(mainMenuAdapter);
    }
    //侧边菜单设置
    private List<MainMenuBean> getMenuData() {
        menuBeans.clear();
        String[] menuName = getResources().getStringArray(R.array.main_menu_name);
        TypedArray menuIcon = getResources().obtainTypedArray(R.array.main_menu_icon);

        for (int i = 0; i < menuName.length; i++) {
            MainMenuBean menuBean = new MainMenuBean();
            menuBean.setName(menuName[i]);
            menuBean.setIcon(menuIcon.getResourceId(i, 0));
            menuBeans.add(menuBean);
        }
        return menuBeans;
    }

    public void switchFragment(String name) {
        if (currentFragmentTag != null && currentFragmentTag.equals(name))
            return;

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

        Fragment foundFragment = fragmentManager.findFragmentByTag(name);

        if (foundFragment == null) {
            switch (name) {
                case "分类":
                    foundFragment = BookClassifyFragment.newInstance();
                    break;
//                case "书架":
//                    foundFragment = BookShelfFragment.newInstance();
//                    break;
//                case "扫描书籍":
//                    foundFragment = ScanBookFragment.newInstance();
//                    break;
                default:
//                    foundFragment = BookShelfFragment.newInstance();
                    break;
            }
        }

        if (foundFragment == null) {

        } else if (foundFragment.isAdded()) {
            ft.show(foundFragment);
        } else {
            ft.add(R.id.container, foundFragment, name);
        }
        ft.commit();
        currentFragmentTag = name;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mResideLayout.isOpen()){
            mResideLayout.closePane();
        }
        Glide.with(mContext).load(R.mipmap.img_header)
                .apply(new RequestOptions().transform(new CircleCrop()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                .into(mIvAvatar);
        mTvDesc.setText("未登录");
        mTvSetting.setText("设置");
    }

    @OnClick({R.id.iv_avatar, R.id.tv_theme, R.id.tv_setting,R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                    JumpUtils.jump(mContext,LoginActivity.class,null);
                break;
            case R.id.tv_theme:
                new ColorChooserDialog.Builder(this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
            case R.id.tv_setting:

//                if (mUsername.equals("")) {
//                    startActivity(LoginActivity.class);
//                } else {
//                    startActivity(SettingActivity.class);
//                }
                break;

            case  R.id.tv_back:
                mResideLayout.openPane();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mResideLayout.isOpen()) {
            mResideLayout.closePane();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - fristTime < 2000) {
                finish();
            } else {
                SnackBarUtils.makeShort(getWindow().getDecorView(), "再点击一次退出应用").show();
                fristTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor2Array(this, R.attr.colorPrimary))
            return;

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);

            SharedPreUtils.getInstance().setCurrentTheme(Theme.Blue);

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Red);

        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Brown);

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Green);

        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Purple);

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Teal);

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Pink);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.DeepPurple);

        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Orange);

        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Indigo);

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.LightGreen);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.DeepOrange);

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Lime);

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.BlueGrey);

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            SharedPreUtils.getInstance().setCurrentTheme(Theme.Cyan);

        }
        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }

    /**
     * 菜单是否可左滑
     *
     * @param isCanSlide
     */
    public void setLeftSlide(boolean isCanSlide) {
        mResideLayout.setCanLeftSlide(isCanSlide);
    }
}
