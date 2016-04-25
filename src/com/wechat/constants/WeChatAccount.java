package com.wechat.constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WeChatAccount {

	// 测试账号信息
	public static final String TOKEN = "weixin";
	public static final String APPID = "wxbf3304ad4cc410d5";
	public static final String APPSECRET = "53b0996b56f9f7b7b5a7d8bb941a3c84";
	
	// get user info
	private static final String get_userInfo_url_template = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	// access token(union id)
	private static final String access_token_url_template = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	
	// get code(OAuth 2.0)
	private static final String get_code_url_template = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		
	// get access token(OAuth 2.0) by code
	private static final String auth_access_token_url_template = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	// refresh access token(OAuth 2.0)
	private static final String refresh_access_token_url_template = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	// get user info(OAuth 2.0, scope = snsapi_userinfo)
	private static final String get_userInfo_auth_url_template = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	// create menu
	private static final String create_menu_url_template = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	// get js api ticket
	private static final String jsapi_ticket_url_template = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/** scope to get user id */
	public static final String scope_snsapi_base = "snsapi_base";
	/** scope to get user info */
	public static final String scope_snsapi_userinfo = "snsapi_userinfo";
	
	public static String getUserInfoUrl(String accessToken, String openId) {
		return get_userInfo_url_template.replace("ACCESS_TOKEN", accessToken)
				.replace("OPENID", openId);
	}
	
	public static String getUserIdUrl(String redirectUrl, String scope, String state) {
		try {
			redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("redirect to (encoded): "+redirectUrl);
		return get_code_url_template.replace("APPID", APPID).replace("REDIRECT_URI", redirectUrl)
				.replace("SCOPE", scope).replace("STATE", state);
	}
	
	public static String getAccessTokenUrl() {
		return access_token_url_template.replace("APPID", APPID).replace("SECRET", APPSECRET);
	}
	
	public static String getAuthAccessTokenUrl(String code) {
		return auth_access_token_url_template.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
	}
	
	public static String getCreateMenuUrl(String accessToken) {
		return create_menu_url_template.replace("ACCESS_TOKEN", accessToken);
	}
	
	public static String getJsApiTicketUrl(String accessToken) {
		return jsapi_ticket_url_template.replace("ACCESS_TOKEN", accessToken);
	}
	
	public static String getUserInfoByAuthUrl(String accessToken, String openId) {
		return get_userInfo_auth_url_template.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	}
	/** get access_token(OAuth 2.0) by refresh token */
	public static String refreshToken(String refreshToken) {
		return refresh_access_token_url_template.replace("APPID", APPID).replace("REFRESH_TOKEN", refreshToken);
	}
	
	public static void main(String[] args) {
		String url = getUserIdUrl("http://newkinglong.duapp.com/SimpleServlet/control/pages/userinfo", scope_snsapi_base, "");
		System.out.println(url);
	}
	
	// 公众账号信息
//	public static final String APPID = "wx29c1ef80766d5192";
//	public static final String APPSECRET = "58a165847d648e4e6e996d87d2b67cdd";

}
