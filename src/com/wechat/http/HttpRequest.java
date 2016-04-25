package com.wechat.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * @Deprecated, using Http to replace
 * @author niejinlong
 *
 */
@Deprecated
public class HttpRequest {

	@Deprecated
	public String doGet(String urlString) {
		URL url;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader reader = null;
		BufferedReader bf = null;
		try {
			url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			inputStream = connection.getInputStream();
			reader = new InputStreamReader(inputStream);
			bf = new BufferedReader(reader);
			StringBuffer reply = new StringBuffer();
			String line = null;
			while ((line = bf.readLine()) != null) {
				reply.append(line);
			}
			return reply.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("HttpRequestException");
			e.printStackTrace();
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;		
	}
	
	@Deprecated
	public static void doPost() {
		
	}
}
