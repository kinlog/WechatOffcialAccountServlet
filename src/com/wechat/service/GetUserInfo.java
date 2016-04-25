package com.wechat.service;

import com.wechat.constants.WeChatAccount;
import com.wechat.entity.reply.UserInfo;
import com.wechat.utils.Http;
import com.wechat.utils.ReplyDetect;

import net.sf.json.JSONObject;

public class GetUserInfo {

	/**
	 * get userinfo with user openid
	 * 
	 * @param openId
	 * @return User
	 */
	public static UserInfo getUserInfo(String openId) {
		if (openId == null || openId.isEmpty()) {
			return null;
		}
		UserInfo user = null;
		try {
			String url = WeChatAccount.getUserInfoUrl(AccessTokenService.getAccessToken(), openId);
			String reply = new Http().doGet(url);
			if (reply != null && !reply.isEmpty()) {
				// right reply
				if (!ReplyDetect.isErrorMessage(reply)) {
					JSONObject object = JSONObject.fromObject(reply);
					user = (UserInfo) JSONObject.toBean(object, UserInfo.class);
					return user;
				}
				// error reply
				else {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// test
	public static void main(String[] args) {
		UserInfo user = getUserInfo("oTCAmw0wkHVMs-d2BYWx_PInIl2g");
//		UserInfo user = getUserInfo("oBkWvwwAsVviQ8Y06MFL-Dya57Ic");
		if (user != null) {
			System.out.println(user.toString());
		}
		
		/*final int len = 28;
		final String chars = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_";
		final int length = chars.length();
		
		
		for ( int i=0; i<1000; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Random random = new Random();
					StringBuffer account = new StringBuffer();
					
					for (int j=0; j<len; j++) {
						account.append(chars.charAt(random.nextInt(length)));
					}
					System.out.println("Get "+account);
					User user = getUserInfo(account.toString());
					if (user != null ) {
						System.out.println(user.toString());
					}
				}
			}).start();
			
		}*/
		
	}
}
