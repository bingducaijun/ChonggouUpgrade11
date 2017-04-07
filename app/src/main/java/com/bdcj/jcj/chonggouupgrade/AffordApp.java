package com.bdcj.jcj.chonggouupgrade;

import android.content.Context;

import com.bdcj.jcj.chonggouupgrade.business.entity.LocationEty;
import com.bdcj.jcj.chonggouupgrade.business.entity.LoginEty;
import com.bdcj.jcj.chonggouupgrade.common.constants.CommConstants;
import com.bdcj.jcj.chonggouupgrade.ui.AffordBean;
import com.bdcj.jcj.chonggouupgrade.util.preferences.UtilPreferences;
import com.ta.TAApplication;

/**
 * Created by Administrator on 2017/3/25.
 */

public class AffordApp extends TAApplication {
    private static TAApplication application;
    private static AffordBean mAffordBean;


    /// **商户实体***///
    private LocationEty locationEty;
    /// **用户登录实体***///
    private LoginEty loginEty;
    /// **实惠币***///
    /// **用户邀请好友***///


    /// **用户信息***///
    public static String LOG_PHONE = "";
    public static String LOG_PWD = "";

    public static AffordApp getInstance() {
        return (AffordApp) getApplication();
    }

    public static AffordBean getmAffordBean() {
        if (mAffordBean == null)
            mAffordBean = AffordBean.getInstance();
        return mAffordBean;
    }

    public LocationEty getLocationEty() {
        return locationEty;
    }

    public void setLocationEty(LocationEty locationEty) {
        this.locationEty = locationEty;
    }

    public LoginEty getLoginEty() {
        return loginEty;
    }

    public void setLoginEty(LoginEty loginEty) {
        this.loginEty = loginEty;
    }

    /**
     * 是否自动登录
     *
     * @param
     * @return
     */
    public static boolean isAutoLogin(Context context) {
        if (UtilPreferences.getBoolean(context, CommConstants.Login.SP_AUTO_LOGIN))
            return true;
        else
            return false;
    }

    /**
     * 清除本地数据
     *
     * @param context
     */
    public static void exitLogin(Context context) {
        //???又不用空字符串了
        LOG_PHONE = null;
        LOG_PWD = null;
        if (UtilPreferences.getAll(context) != null) {
            UtilPreferences.clearData(context);
        }
    }
}
