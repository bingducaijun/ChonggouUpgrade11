package com.bdcj.jcj.chonggouupgrade.common.constants;

/**
 * Created by Administrator on 2017/3/28. 永恒的，始终如一的;
 */

public class AffConstants
{
	public static class PUBLIC
	{
		public static final String RESULT_STATE_SUCCESS = "0";// 成功返回标识
		public static final String RESULT_STATE_ERROR = "-1";// 错误返回标识
		public static final String RESULT_STATE_RIGEDIT_ERROR = "102";// 重复注册
		public static final String RESULT_STATE_MOBLILERIGEDIT_ERROR = "901";// 手机没有注册，需要退出重新登录

		public static final String ADDRESS = "http://api.91dashihui.com/";// 服务地址
		public static final String ADDRESS_IMAGE = "http://static.91dashihui.com";// 服务地址

		public static final int TIMEOUT = 1000 * 15;
		public static final int CACHETIME = 1000 * 5;
		public static final String INFO_AK = "S2kGRESvR7jHYhPI8XEfZylv";// 大实惠服务端数据（??）
		public static final int INFO_GEOTABLEDID = 125217;// DSHData 表ID ??
		public static final int INFO_RADIUS = 900000;// 范围 ？？

		public static final String APP_CHANGE_DIR_NAME = "android/com.dashihui.afford";
	}

	public static class BUSINESS
	{
		private final static String COMMON = "common/";
		private final static String GOODS = "goods/";
		private final static String AD = "ad/";
		private final static String USER = "user/";
		private final static String ORDER = "order/";
		private final static String SEARCH = "search";
		private final static String SERVICE = "service/";
		private final static String SER = "ser/";
		private final static String STORE = "store/";

		private final static int TAG_CODE = 1000;// ???

		/** 服务api公共接口 **/
		public final static String COMMON_REGISTER = COMMON + "regist";
		public final static String COMMON_GETCOMMUNITY=COMMON+"getCommunity";// 获取登录用户所在社区的社区及店铺信息
		/******* 商品接口 ********/
		/******* 商铺详情 ********/
		/******* 店铺通知查询 ********/
		/******* 用户接口 ********/
		public final static String USER_LOGIN = USER + "login";
		/****************** 校验验证码 **************/
		public final static String USER_RESETPWD = USER + "resetPwd";// 修改密码

		/******* 订单接口 ********/
		/******* 搜索接口 ********/
		/******* 服务接口 ********/
		/******* 服务家政接口？？？？ ********/



		/**===================== UI协议唯一标识 ========================*/
		public final static int TAG_COMMON_REGISTER = TAG_CODE + 1;
		public final static int TAG_COMMON_GETCOMMUNITY=TAG_CODE+11;


		/******* 商品 ********/
		public final static int TAG_USER_LOGIN = TAG_CODE + 9;// 用户登录//放商品里？？？

		/******* 订单接口 ********/
		/******* 搜索接口 ********/
		/******* 服务接口 ********/
		/***************/
		/***************/
		/***************/
		/***************/

	}

}
