package com.bdcj.jcj.chonggouupgrade.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bdcj.jcj.chonggouupgrade.AffordApp;
import com.bdcj.jcj.chonggouupgrade.R;
import com.bdcj.jcj.chonggouupgrade.business.BusinessCommon;
import com.bdcj.jcj.chonggouupgrade.business.BusinessUser;
import com.bdcj.jcj.chonggouupgrade.business.entity.LocationEty;
import com.bdcj.jcj.chonggouupgrade.business.entity.LoginEty;
import com.bdcj.jcj.chonggouupgrade.business.entity.SendToUIEty;
import com.bdcj.jcj.chonggouupgrade.common.base.BaseActivity;
import com.bdcj.jcj.chonggouupgrade.common.constants.AffConstants;
import com.bdcj.jcj.chonggouupgrade.common.constants.CommConstants;
import com.bdcj.jcj.chonggouupgrade.util.UtilCommon;
import com.bdcj.jcj.chonggouupgrade.util.UtilEncryptionDecryption;
import com.bdcj.jcj.chonggouupgrade.util.preferences.UtilPreferences;
import com.bdcj.jcj.chonggouupgrade.util.toast.UtilToast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatReportStrategy;
import com.tencent.stat.StatService;

import java.util.Map;

public class WelcomeActivity extends BaseActivity
{
	private BusinessCommon businessCommon;
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
		businessCommon = new BusinessCommon(this);
		businessCommon.mobileRegister();
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
	public void onFailure(SendToUIEty failureEty)
	{
		mLytWelcome.setVisibility(View.GONE);
		mRlyNoNetwork.setVisibility(View.VISIBLE);
	}

	@Override
	public void onSuccess(SendToUIEty successEty)
	{
		if (successEty == null)
		{
			return;
		}
		switch (successEty.getTag())
		{
		case AffConstants.BUSINESS.TAG_COMMON_REGISTER:// 手机注册成功
			LogUtils.e("onSuccess======手机注册成功========>" + successEty);
			Map<String, Object> registerBean = (Map<String, Object>) successEty.getInfo();
			AffordApp.getInstance().getmAffordBean().setSIGNATURE(registerBean.get("SIGNATURE") + "");// 应该提到AffConstants
			// 检查是否设置了重新登陆
			if (!AffordApp.isAutoLogin(this))
			{
				isFirstRun();
				return;
			}
			if (UtilPreferences.getString(this, CommConstants.Login.SP_AUTO_LOGIN_TIME) == null)
			{
				AffordApp.exitLogin(this);
				isFirstRun();
				return;
			}
			if (UtilPreferences.getString(this, CommConstants.Login.SP_USER_NAME) == null || UtilPreferences.getString(this, CommConstants.Login.SP_USER_PWD) == null)
			{
				AffordApp.exitLogin(this);
				isFirstRun();
				return;
			}
			// 启动登录
			BusinessUser businessUser = new BusinessUser(WelcomeActivity.this);
			try
			{
				UtilEncryptionDecryption encry = new UtilEncryptionDecryption();
				String userName = UtilPreferences.getString(this, CommConstants.Login.SP_USER_NAME);
				String userPwd = encry.decrypt(UtilPreferences.getString(this, CommConstants.Login.SP_USER_PWD));
				businessUser.login(userName, userPwd);
			} catch (Exception e)
			{
				LogUtils.e("解密后的密码====>" + e.getMessage());
				e.printStackTrace();
			}
			break;
		case AffConstants.BUSINESS.TAG_USER_LOGIN:
			LoginEty loginEty = (LoginEty) successEty.getInfo();
			if (loginEty == null)// 自动登录的可能性较低
			{
				AffordApp.exitLogin(this);
				isFirstRun();
				break;
			}
			LogUtils.e("登录成功TOKEN=======>" + loginEty.getToken());
			AffordApp.getInstance().setLoginEty(loginEty);
			UtilPreferences.getString(this, CommConstants.Login.SP_USER_TOKEN, loginEty.getToken());// 用户签名是变化的么？？？待测试
			UtilEncryptionDecryption encry;
			try
			{
				encry = new UtilEncryptionDecryption();
				// 读取本地存储的用户名及密码
				if (UtilPreferences.getString(this, CommConstants.Login.SP_USER_NAME) != null && UtilPreferences.getString(this, CommConstants.Login.SP_USER_PWD) != null)
				{
					AffordApp.LOG_PHONE = UtilPreferences.getString(this, CommConstants.Login.SP_USER_NAME);
					AffordApp.LOG_PWD = encry.decrypt(UtilPreferences.getString(this, CommConstants.Login.SP_USER_PWD));
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				// 转到首页
				isFirstRun();
				// startHome(true);
			}
			LogUtils.e("entityLogin==========2222===>" + loginEty.getUser());
			// 根据社区ID锁定所在小区的店铺信息
			if (loginEty.getUser() != null)
			{
				businessCommon.getCommunity(loginEty.getUser().getCOMMUNITYID() + "");
			}
			break;
		case AffConstants.BUSINESS.TAG_COMMON_GETCOMMUNITY:
			LogUtils.e("onSuccess======定位本地商铺，社区信息========>" + successEty);
			LocationEty locationEty = (LocationEty) successEty.getInfo();
			AffordApp.getInstance().setLocationEty(locationEty);
			// 转到首页
			isFirstRun();
			break;
		default:
			// 转到首页
			isFirstRun();
			break;
		}

	}

	private void isFirstRun()
	{
		SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
		// 判断是否是第一次运行，如果不是第一次则用true标记，并写入isFirstRun标记，下次启动程序时做判断
		boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		if (isFirstRun)
		{
			startNavi();
			LogUtils.e("===================>第一次运行");
			editor.putBoolean("isFirstRun", false);
			editor.commit();
		} else
		{
			startHome();
			LogUtils.e("===================>不是第一次运行");
		}
	}

	/**
	 * 原版有个没用的变量
	 */
	private void startHome()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(1500);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				// if (isStart)
				// {
				mBaseUtilAty.startActivity(HomeActivity.class);
				// } else
				// {
				// mBaseUtilAty.startActivity(AtyLocation.class);
				// }
				WelcomeActivity.this.finish();
			}
		}.start();
	}

	/**
	 * 原版有个没用的变量
	 */
	private void startNavi()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(1500);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				mBaseUtilAty.startActivity(NavigationActivity.class);
				WelcomeActivity.this.finish();
			}
		}.start();
	}

	@OnClick(R.id.net_again)
	public void onGetNetWorkClick(View v)
	{
		if (UtilCommon.isNetworkAvailable(this))
		{
			// UtilToast.show(getApplicationContext(), "当前有可用网络！",
			// Toast.LENGTH_SHORT);
			mLytWelcome.setVisibility(View.VISIBLE);
			mRlyNoNetwork.setVisibility(View.GONE);
			mBaseUtilAty.startActivity(HomeActivity.class);
		} else
		{
			UtilToast.show(getApplicationContext(), R.string.home_examine, Toast.LENGTH_SHORT);
		}
	}
}
