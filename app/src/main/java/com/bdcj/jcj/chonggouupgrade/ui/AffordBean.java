package com.bdcj.jcj.chonggouupgrade.ui;

/**
 * Created by Administrator on 2017/4/5.
 */

public class AffordBean
{
	/** 手机注册唯一标识 */
	private String SIGNATURE = "";
	private static AffordBean instance;

    public String getSIGNATURE() {
        return SIGNATURE;
    }

    public void setSIGNATURE(String SIGNATURE) {
        this.SIGNATURE = SIGNATURE;
    }

    public static AffordBean getInstance()
	{
		if (instance == null)
		{
			instance = new AffordBean();
		}
		return instance;
	}
}
