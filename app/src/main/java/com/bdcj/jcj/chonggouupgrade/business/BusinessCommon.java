package com.bdcj.jcj.chonggouupgrade.business;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.bdcj.jcj.chonggouupgrade.R;
import com.bdcj.jcj.chonggouupgrade.common.base.BaseActivity;
import com.bdcj.jcj.chonggouupgrade.common.constants.AffConstants;
import com.lidroid.xutils.http.RequestParams;

/**
 * Created by Administrator on 2017/3/25.
 */

public class BusinessCommon extends BusinessBase{
    private BaseActivity mContext;

    public BusinessCommon(BaseActivity mContext) {
        this.mContext = mContext;
    }
    /**
     * 手机唯一标识注册
     */
    public void mobileRegister() {
        //如果成功返回UI层Map类型
        RequestParams params = new RequestParams();
        // 初始化全局异常处理类
        TelephonyManager mTm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        // 手机品牌
        params.addQueryStringParameter("BRAND", android.os.Build.BRAND);
        // 手机型号
        params.addQueryStringParameter("MODEL", android.os.Build.MODEL);
        // 操作系统
        params.addQueryStringParameter("OS", "Android");
        // 系统版本
        params.addQueryStringParameter("OSVERSION", android.os.Build.VERSION.RELEASE);
        metric = new DisplayMetrics();

        mContext.getWindowManager().getDefaultDisplay().getMetrics(metric);
        // 屏幕分辨率
        params.addQueryStringParameter("RESOLUTION", "" + metric.widthPixels + "x" + metric.heightPixels);
        // 屏幕密度
        params.addQueryStringParameter("MIDU", metric.density + "");
        // 设备识别号，手机IMEI号
        params.addQueryStringParameter("UQID", mTm.getDeviceId());
        // 系统ID号
        params.addQueryStringParameter("OSID", android.os.Build.ID);

        params.addQueryStringParameter("TVERSION", mContext.getResources().getString(R.string.versionName));
        params.addQueryStringParameter("TYPE", "A");
        params.addQueryStringParameter("VERSION", mContext.getResources().getString(R.string.versionName));
        send(AffConstants.BUSINESS.COMMON_REGISTER, params, mContext, AffConstants.BUSINESS.TAG_COMMON_REGISTER, SEND_MAP);
    }

}
