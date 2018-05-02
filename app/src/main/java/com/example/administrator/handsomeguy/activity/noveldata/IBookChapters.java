package com.example.administrator.handsomeguy.activity.noveldata;


import com.example.administrator.handsomeguy.dialog_interface.IBaseLoadView;
import com.example.handsomelibrary.model.BookChaptersBean;

/**
 * Created by Liang_Lu on 2017/12/11.
 */

public interface IBookChapters extends IBaseLoadView {
    void bookChapters(BookChaptersBean bookChaptersBean);

    void finishChapters();

    void errorChapters();

}
