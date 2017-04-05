package com.bdcj.jcj.chonggouupgrade.business.entity;

/**
 * Created by Administrator on 2017/3/28.
 */

public class ResponseEty {
    private String state;
    private String msg;
    private Object oject;

    public ResponseEty() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOject() {
        return oject;
    }

    public void setOject(Object oject) {
        this.oject = oject;
    }
}
