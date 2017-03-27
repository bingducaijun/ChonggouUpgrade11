package com.bdcj.jcj.chonggouupgrade.common.base;

import com.bdcj.jcj.chonggouupgrade.business.entity.SendToUIEty;

/**
 * Created by Administrator on 2017/3/25.
 */

public interface RequestCallback<T>
{
	public void onSuccess(SendToUIEty successEty);

	public void onFailure(SendToUIEty failureEty);
}
