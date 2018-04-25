package com.example.administrator.handsomeguy.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.handsomeguy.R;
import com.example.administrator.handsomeguy.apputils.DimenUtils;
import com.example.handsomelibrary.model.MainMenuBean;

import java.util.List;

/**
 * Created by Stefan on 2018/4/25.
 */

public class MainMenuAdapter extends BaseQuickAdapter<MainMenuBean,BaseViewHolder>{

    public MainMenuAdapter( @Nullable List<MainMenuBean> data) {
        super(R.layout.adapter_main_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainMenuBean item) {
        TextView mTvName=helper.getView(R.id.tv_name);
        mTvName.setText(item.getName());
        mTvName.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(item.getIcon()),null,null,null);
        mTvName.setCompoundDrawablePadding(DimenUtils.dp2px(10));
    }
}
