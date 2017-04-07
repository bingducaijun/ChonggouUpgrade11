package com.bdcj.jcj.chonggouupgrade.business;

import com.bdcj.jcj.chonggouupgrade.AffordApp;
import com.bdcj.jcj.chonggouupgrade.business.entity.LocationEty;
import com.bdcj.jcj.chonggouupgrade.common.base.AffRequestCallback;
import com.bdcj.jcj.chonggouupgrade.common.constants.AffConstants;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2017/4/6.
 */

public class BusinessUser extends BusinessBase {
    private AffRequestCallback mCallback;
    private LocationEty mLocation;

    public BusinessUser(AffRequestCallback mCallback) {
        super();
        this.mCallback = mCallback;
    }

    public void login(String userName, String pwd) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("PHONE", userName);
        params.addQueryStringParameter("PASSWORD", pwd);
        userSend(AffConstants.BUSINESS.USER_LOGIN, params, mCallback, AffConstants.BUSINESS.TAG_USER_LOGIN, SEND_ENTITYLOGIN);
    }

    private void userSend(String uriPath, RequestParams params, final AffRequestCallback affCallback, final int tag, final int sendType) {
        if (params == null) {
            params = new RequestParams();
        }
        mLocation= AffordApp.getInstance().getLocationEty();
        if (mLocation!=null)
        {
            LogUtils.e("STOREID====店铺ID===>" + mLocation.getStore().getID());
            params.addQueryStringParameter("STOREID",mLocation.getStore().getID()+"");
        }
        if (AffordApp.getInstance()!=null&&AffordApp.getInstance().getLoginEty()!=null)
        {
            params.addQueryStringParameter("STOREID",AffordApp.getInstance().getLoginEty().getToken()+"");
        }
        send(uriPath,params,mCallback,tag,sendType);
    }

}
