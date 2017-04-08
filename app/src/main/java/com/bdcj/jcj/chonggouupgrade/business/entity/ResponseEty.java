package com.bdcj.jcj.chonggouupgrade.business.entity;

/**
 * Created by Administrator on 2017/3/28.
 * 返回结果{"MSG":"","STATE":"0","OBJECT":{"SIGNATURE":"9c2358bc7d0a4085a8a1bdff151ba23c5565"}}
 * 所以作为json转化的实体类应该对应
 */

public class ResponseEty
{
	private String STATE;
	private String MSG;
	private Object OBJECT;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public Object getOBJECT() {
        return OBJECT;
    }

    public void setOBJECT(Object OBJECT) {
        this.OBJECT = OBJECT;
    }
}
