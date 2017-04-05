package com.bdcj.jcj.chonggouupgrade.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bdcj.jcj.chonggouupgrade.AffordApp;
import com.bdcj.jcj.chonggouupgrade.R;
import com.bdcj.jcj.chonggouupgrade.business.BusinessCommon;
import com.bdcj.jcj.chonggouupgrade.business.entity.SendToUIEty;
import com.bdcj.jcj.chonggouupgrade.common.base.BaseActivity;
import com.bdcj.jcj.chonggouupgrade.common.constants.AffConstants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatReportStrategy;
import com.tencent.stat.StatService;

import java.util.Map;

public class WelcomeActivity extends BaseActivity
{
	private BusinessCommon mBusinessCommon;
	@ViewInject(R.id.net_again)
	private TextView mTvBtnNetAgain;
	@ViewInject(R.id.rlyt_nonetwork)
	private RelativeLayout mRlyNoNetwork;
	@ViewInject(R.id.lyt_welcome)
	private LinearLayout mLytWelcome;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		ViewUtils.inject(this);
		mBusinessCommon = new BusinessCommon(this);
		mBusinessCommon.mobileRegister();
		initMTConfig();
	}

	/**
	 * 测试分析bug
	 */
	private void initMTConfig()
	{
		boolean IsDebug = false;
		if (IsDebug)
			initMTConfig(true);
		else
			initMTConfig(false);
		String appKey = "AKN3552SNZGZ";
		try
		{
			StatService.startStatService(this, appKey, com.tencent.stat.common.StatConstants.VERSION);
		} catch (MtaSDkException e)
		{
			LogUtils.e("error=============>腾讯云分析");
			e.printStackTrace();
		}
	}

	/**
	 * 腾讯云分析MTA ---根据不同的模式，建议设置的开关状态，可根据实际情况调整，仅供参考。
	 *
	 * @param isDebugMode
	 *            根据调试或发布条件，配置对应的MTA配置
	 */
	private void initMTConfig(boolean isDebugMode)
	{
		if (isDebugMode)
		{ // 调试时建议设置的开关状态
			// 查看MTA日志及上报数据内容
			StatConfig.setDebugEnable(true);
			// 禁用MTA对app未处理异常的捕获，方便开发者调试时，及时获知详细错误信息。
			StatConfig.setAutoExceptionCaught(false);
			// 关闭wifi下及时上报数据
			StatConfig.setEnableSmartReporting(false);
			// 调试时，使用实时发送
			StatConfig.setStatSendStrategy(StatReportStrategy.INSTANT);
		} else
		{ // 发布时，建议设置的开关状态，请确保以下开关是否设置合理
			// 禁止MTA打印日志
			StatConfig.setDebugEnable(false);
			// 根据情况，决定是否开启MTA对app未处理异常的捕获
			StatConfig.setAutoExceptionCaught(true);
			// 选择默认的上报策略
			StatConfig.setStatSendStrategy(StatReportStrategy.APP_LAUNCH);
			// 关闭wifi下及时上报数据
			StatConfig.setEnableSmartReporting(true);
		}
	}

	@Override
	public void onSuccess(SendToUIEty successEty)
	{
		if (successEty == null)
		{
			return;
		}
        switch (successEty.getTag()){
            case AffConstants.BUSINESS.TAG_COMMON_REGISTER://手机注册成功
                LogUtils.e("onSuccess======手机注册成功========>" + successEty);
                Map<String,Object> registerBean= (Map<String, Object>) successEty.getInfo();
                AffordApp.getInstance().getmAffordBean().setSIGNATURE(registerBean.get("SIGNATURE")+"");//应该提到AffConstants
                break;
        }

	}

	@Override
	public void onFailure(SendToUIEty failureEty)
	{

	}
}
