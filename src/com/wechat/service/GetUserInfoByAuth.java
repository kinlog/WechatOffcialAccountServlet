package com.wechat.service;

import com.wechat.constants.WeChatAccount;
import com.wechat.entity.reply.AccessTokenByCode;
import com.wechat.entity.reply.UserInfoByAuth;
import com.wechat.utils.Http;
import com.wechat.utils.ReplyDetect;

import net.sf.json.JSONObject;

public class GetUserInfoByAuth {

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @return
	 */
	public static UserInfoByAuth getUserInfo(String accessToken, String openId) {
		String url = WeChatAccount.getUserInfoByAuthUrl(accessToken, openId);
		String reply = new Http().doGet(url);
		// right reply
		if (!ReplyDetect.isErrorMessage(reply)) {
			JSONObject json = JSONObject.fromObject(reply);
			UserInfoByAuth userInfo = (UserInfoByAuth) JSONObject.toBean(json, UserInfoByAuth.class);
			return userInfo;
		} else {

		}
		return null;
	}

	public void refreshAccessToken() {

	}

	public static AccessTokenByCode getAccessToken(String code) {
		String url = WeChatAccount.getAuthAccessTokenUrl(code);
		String reply = new Http().doGet(url);
		// right reply
		if (!ReplyDetect.isErrorMessage(reply)) {
			JSONObject json = JSONObject.fromObject(reply);
			AccessTokenByCode rsp = (AccessTokenByCode) JSONObject.toBean(json, AccessTokenByCode.class);
			return rsp;
		}
		// error happen
		else {

		}
		return null;
	}

	public static void main(String[] args) {

	}

}
