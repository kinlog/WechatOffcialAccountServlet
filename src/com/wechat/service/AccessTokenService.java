package com.wechat.service;

import java.util.Timer;
import java.util.TimerTask;

import com.wechat.constants.WeChatAccount;
import com.wechat.entity.reply.AccessToken;
import com.wechat.utils.Http;
import com.wechat.utils.ReplyDetect;

import net.sf.json.JSONObject;

/**
 * store a global access_token
 * 
 * @author niejinlong
 *
 */
public class AccessTokenService {

	/** access token (union 机制) */
	private static String accessToken = null;
	
	// 2 hours, unit: seconds
	private static long expireIn = 7200L;

	/*static{
		refreshToken();
	}*/

	/*private static void refreshToken() {
	Timer timer = new Timer();
	timer.schedule(new TimerTask() {

		@Override
		public void run() {
			getToken();
		}
	}, 0, new Double(expireIn * 990).longValue());
	}*/
	
	/**
	 * set token expire in a special period
	 */
	private static void setExpire() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				accessToken = null;
			}
		}, new Double(expireIn * 990).longValue());
	}

	/**
	 * get token from network
	 */
	private static void getToken() {
		String url = WeChatAccount.getAccessTokenUrl();
		String reply = new Http().doGet(url);
		if (reply != null && !reply.isEmpty()) {
			// right reply
			if (!ReplyDetect.isErrorMessage(reply)) {
				JSONObject json = JSONObject.fromObject(reply);
				AccessToken token = (AccessToken) JSONObject.toBean(json, AccessToken.class);
				accessToken = token.getAccess_token();
				expireIn = token.getExpires_in();
				System.out.println("<check>Get token: " + accessToken);
			}
			// error reply
			else {
				
			}
		}
	}

	/**
	 * interface for get access_token
	 * @return access_token
	 */
	public static String getAccessToken() {
		if (accessToken == null || accessToken.isEmpty()) {
			getToken();
			setExpire();
		}
		return accessToken;
	}

}
