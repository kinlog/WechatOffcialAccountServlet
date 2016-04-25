package com.wechat.utils;

import java.util.Scanner;

import net.sf.json.JSONObject;

public class ReplyDetect {

	// if this string is found is respond, then it's an error respond
	private static final String ErrorFeature = "\"errcode\":";

	/**
	 * test if a message(json) is an error message
	 * json消息如果满足下面的所有要求，即视为错误消息
	 * 	1. 含有特征串 errcode: 
	 * 	2. errcode != 0
	 * 
	 * @param jsonMessage
	 * @return isError
	 */
	public static boolean isErrorMessage(String jsonMessage) {
		if (jsonMessage.indexOf(ErrorFeature) < 0) {
			return false;
		}
		JSONObject json = JSONObject.fromObject(jsonMessage);
		int errcode = (int) json.getInt("errcode");
		if (errcode == 0) {
			return false;
		}
		return true;
	}

	// test
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		while (in.hasNextLine()) {
			String s = in.nextLine();
			if ("END".equals(s)) break;
			sb.append(s);
		}
		System.out.println(isErrorMessage(sb.toString()));
		in.close();
	}

}
