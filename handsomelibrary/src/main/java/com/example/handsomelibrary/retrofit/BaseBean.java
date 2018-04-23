package com.example.handsomelibrary.retrofit;

import java.io.Serializable;

/**
 * 网络请求 返回数据 格式化对象
 * Created by fangs on 2017/11/6.
 */
public class BaseBean<T> implements Serializable{
    /**
     * msg : 操作成功
     * code : 0
     * rows : {"token":"3011bbfa26bea40ef490d281e7197282"}
     */
    private String msg = "";
    private int code = -1;
    private T rows;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    /**
     * 判断请求是否成功
     * @return
     */
    public boolean isSuccess(){
        return code == 0;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", rows=" + rows +
                '}';
    }
}
