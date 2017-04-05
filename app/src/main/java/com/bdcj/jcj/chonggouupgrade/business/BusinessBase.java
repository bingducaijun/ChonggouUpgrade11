package com.bdcj.jcj.chonggouupgrade.business;

import com.bdcj.jcj.chonggouupgrade.AffordApp;
import com.bdcj.jcj.chonggouupgrade.business.entity.ResponseEty;
import com.bdcj.jcj.chonggouupgrade.business.entity.SendToUIEty;
import com.bdcj.jcj.chonggouupgrade.common.base.AffRequestCallback;
import com.bdcj.jcj.chonggouupgrade.common.constants.AffConstants;
import com.bdcj.jcj.chonggouupgrade.thirdapi.FastJSONHelper;
import com.bdcj.jcj.chonggouupgrade.util.String.UtilString;
import com.bdcj.jcj.chonggouupgrade.util.json.UtilJSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2017/3/28.
 */

public class BusinessBase
{
	protected final static int SEND_MAP = 0;
	protected final static int SEND_LISTMAP = 1;
	protected final static int SEND_ENTITYLOCTION = 2;
	protected final static int SEND_ENTITYLOGIN = 3;
	protected final static int SEND_ETYSHOPDETAIL = 4;
	protected final static int SEND_ETYBUILDING = 5;
	protected final static int SEND_ORDER = 6;
	protected final static int SEND_ETYLIST = 7;
	protected final static int SEND_ETYSERVERDETAIL = 8;
	protected final static int SEND_ETYSERVERTIME = 9;
	private final static String APIVERSION = "1.3.3";
	private HttpUtils httpUtils;

	/**
	 * 设置httpUtils参数
	 */
	public BusinessBase()
	{
		if (httpUtils == null)
		{
			httpUtils = new HttpUtils(AffConstants.PUBLIC.TIMEOUT);
		}
		httpUtils.configCurrentHttpCacheExpiry(AffConstants.PUBLIC.CACHETIME);
	}

	public void send(String uriPath, RequestParams params, final AffRequestCallback affRequestCallback, final int tag, final int sendType)
	{
		if (params == null)
		{
			params = new RequestParams();
		}
		params.addQueryStringParameter("APIVERSION", APIVERSION);
		send(uriPath, params, new RequestCallBack<Object>()
		{

			@Override
			public void onSuccess(ResponseInfo<Object> responseInfo)
			{
				ResponseEty mBean;
				String jsonResult = (String) responseInfo.result;
				SendToUIEty sendUi = new SendToUIEty();
				sendUi.setTag(tag);
				LogUtils.e("返回JSON字符串结果=========>" + jsonResult);
				if (UtilString.isEmpty(jsonResult))
				{
					mBean = FastJSONHelper.deserialize(jsonResult, ResponseEty.class);
				} else
				{
					sendUi.setInfo("服务器返回数据是null");
					affRequestCallback.onFailure(sendUi);
					return;
				}
				if (mBean == null)
				{
					sendUi.setInfo("JSON解析异常");
					return;
				}
				switch (mBean.getState())
				{
				case AffConstants.PUBLIC.RESULT_STATE_SUCCESS:
					affRequestCallback.onSuccess(getSuccessEtyByType(sendType, mBean, sendUi));
					break;
				case AffConstants.PUBLIC.RESULT_STATE_ERROR:
					sendUi.setInfo(mBean.getState());
					affRequestCallback.onFailure(sendUi);
					break;
				case AffConstants.PUBLIC.RESULT_STATE_RIGEDIT_ERROR:
					sendUi.setInfo(mBean.getState());
					affRequestCallback.onFailure(sendUi);
					break;
				case AffConstants.PUBLIC.RESULT_STATE_MOBLILERIGEDIT_ERROR:
					sendUi.setInfo("数据异常，请重新启动app");
					affRequestCallback.onFailure(sendUi);
					break;
				default:
					sendUi.setInfo("特殊情况");
					affRequestCallback.onFailure(sendUi);
					break;
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
				SendToUIEty sendToUIEty = new SendToUIEty();
				sendToUIEty.setTag(tag);
				sendToUIEty.setInfo("服务协议异常");
				affRequestCallback.onFailure(sendToUIEty);
			}
		});

	}

	private SendToUIEty getSuccessEtyByType(int sendType, ResponseEty mBean, SendToUIEty sendToUIEty)
	{
		switch (sendType)
		{
		case SEND_MAP:
			return sendToUIEty.setInfo(UtilJSON.parseKeyAndValueToMap(mBean.getOject() + ""));
		case SEND_LISTMAP:
			return null;
		case SEND_ENTITYLOCTION:
			return null;
		case SEND_ENTITYLOGIN:
			return null;
		case SEND_ETYSHOPDETAIL:
			return null;
		case SEND_ETYBUILDING:
			return null;
		case SEND_ORDER:
			return null;
		case SEND_ETYLIST:
			return null;
		case SEND_ETYSERVERDETAIL:
			return null;
		case SEND_ETYSERVERTIME:
			return null;
		default:
			return null;
		}
	}

	private <T> HttpHandler<T> send(String uriPath, RequestParams params, RequestCallBack requestCallBack)
	{
		if (params == null)
			params = new RequestParams();
		params.addQueryStringParameter("SIGNATURE", AffordApp.getmAffordBean().getSIGNATURE());
		return httpUtils.send(HttpRequest.HttpMethod.POST, AffConstants.PUBLIC.ADDRESS + uriPath, params, requestCallBack);
	}
}
