package com.bdcj.jcj.chonggouupgrade.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bdcj.jcj.chonggouupgrade.R;
import com.bdcj.jcj.chonggouupgrade.ui.widget.WdtProDialog;
import com.bdcj.jcj.chonggouupgrade.util.network.ConnectivityReceiver;
import com.tencent.stat.StatService;

public abstract class BaseActivity extends AppCompatActivity implements RequestCallback
{
	protected WdtProDialog mProDialog = null;
	public static ConnectivityReceiver mConnReceiver = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
	}

	/**
	 * 显示过度加载狂
	 * 
	 * @param context
	 */
	protected void showProdialog(Context context)
	{
		if (mProDialog != null && mProDialog.isShowing())
		{
			return;
		}
		if (mProDialog == null)
		{
			mProDialog = WdtProDialog.createDialog(context);
		}
		mProDialog.show();
	}

	protected void dissProDialog()
	{
		if (mProDialog != null && mProDialog.isShowing())
		{
			mProDialog.dismiss();
		}
	}

    @Override
    protected void onResume() {
        super.onResume();
        //腾讯统计
		StatService.onResume(this);
		if (mConnReceiver==null){
			mConnReceiver=new ConnectivityReceiver(this);
			mConnReceiver.setOnNetworkAvailableListener(new ConnectivityReceiver.OnNetworkAvailableListener() {
				@Override
				public void onNetworkAvailable() {

				}

				@Override
				public void onNetworkUnavailable() {

				}
			});
		}
		mConnReceiver.bind(this);
    }

	@Override
	protected void onPause() {
		super.onPause();
		if(mConnReceiver!=null){
			mConnReceiver.unbind(this);
			mConnReceiver=null;
		}
	}

	/**
	 * 底部购物车显示==未写
	 */
	protected void sendShopChartBroadcast(){

	}
}
