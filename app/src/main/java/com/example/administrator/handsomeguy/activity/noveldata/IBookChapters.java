package com.example.administrator.handsomeguy.activity.noveldata;


import com.example.administrator.handsomeguy.dialog_interface.IBaseLoadView;
import com.example.handsomelibrary.model.BookChaptersBean;

/**
 * Created by Stefan on 2018/5/2.
 */

public interface IBookChapters extends IBaseLoadView {
    void bookChapters(BookChaptersBean bookChaptersBean);

    void finishChapters();

    void errorChapters();

}
