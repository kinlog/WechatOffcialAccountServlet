package com.wechat.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.wechat.constants.WeChatAccount;
import com.wechat.utils.Http;
import com.wechat.utils.Http.ContentType;

/**
 * create custom Menu
 * 
 * @author niejinlong
 *
 */
public class CreateMenu {
	
	private static Logger logger = Logger.getLogger(Http.class);
	
	/**
	 * create menu with style
	 * @param style(json string)
	 */
	public static void create(String style) {
		String url = WeChatAccount.getCreateMenuUrl(AccessTokenService.getAccessToken());
		String reply = new Http().doPostText(url, style, ContentType.JSON);
		System.out.println("reply: " + reply);
	}
	
	// for test
	public static void main(String[] args) {
		
		logger.info("<check>");
		
		StringBuffer style = new StringBuffer();
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line =in.nextLine();
			if (line.equals("END")) break;
			style.append(line);
		}
		in.close();
		
		create(style.toString());
	}
}
