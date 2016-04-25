package com.wechat.service;

import java.util.Timer;
import java.util.TimerTask;

import com.wechat.constants.WeChatAccount;
import com.wechat.entity.reply.JsapiTicket;
import com.wechat.utils.Http;
import com.wechat.utils.ReplyDetect;

import net.sf.json.JSONObject;

/**
 * store a global jsapi_ticket
 * 
 * @author niejinlong
 *
 */
public class JsApiTicketService {

	private static String jsapiTicket = null;

	// 2 hours, unit: seconds
	private static long expireIn = 7200L;
	
	private static void setExpire() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				jsapiTicket = null;
			}
		}, new Double(expireIn * 990).longValue());
	}

	private static void getTicket() {
		String url = WeChatAccount.getJsApiTicketUrl(AccessTokenService.getAccessToken());
		String reply = new Http().doGet(url);
		if (reply != null && !reply.isEmpty()) {
			// right reply
			if (!ReplyDetect.isErrorMessage(reply)) {
				JSONObject json = JSONObject.fromObject(reply);
				JsapiTicket ticket = (JsapiTicket) JSONObject.toBean(json, JsapiTicket.class);
				jsapiTicket = ticket.getTicket();
				expireIn = ticket.getExpires_in();
			}
			// error reply
			else {
				
			}
		}
	}

	/**
	 * interface for get jsapi_ticket
	 * @return jsapi_ticket
	 */
	public static String getJsApiTicket() {
		if (jsapiTicket == null || jsapiTicket.isEmpty()) {
			getTicket();
			setExpire();
		}
		return jsapiTicket;
	}
	
}
