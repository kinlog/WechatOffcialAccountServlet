package com.wechat.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * httpclient get & post
 * 
 * @author niejinlong
 *
 */
public class Http {
	
	public static enum ContentType {
		JSON, XML
	}

	public String doGet(String url) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String content = EntityUtils.toString(entity, "UTF-8");
				httpGet.abort();
				return content;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public String doPostForm(String url, @SuppressWarnings("rawtypes") Map params) {
		// 创建默认的 HttpClient 实例
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);

		// set params
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Object key : params.keySet()) {
				formParams.add(new BasicNameValuePair((String) key, (String) params.get(key)));
			}
		}
		UrlEncodedFormEntity urlEncodedFormEntity;

		try {
			urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String content = null;
			if (httpEntity != null) {
				content = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("Response content:" + content);
			}
			httpPost.abort();
			return content;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public String doPostText(String url, String text, ContentType type) {
		// 创建默认的 HttpClient 实例
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);
		HttpEntity entity = null;
		if (type == ContentType.JSON) {
			entity = EntityBuilder.create().setContentType(org.apache.http.entity.ContentType.APPLICATION_JSON)
					.setText(text).build();
		} else if (type == ContentType.XML) {
			entity = EntityBuilder.create().setContentType(org.apache.http.entity.ContentType.APPLICATION_XML)
					.setText(text).build();
		}
		try {
			httpPost.setEntity(entity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String content = null;
			if (httpEntity != null) {
				content = EntityUtils.toString(httpEntity, "UTF-8");
			}
			httpPost.abort();
			return content;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	/**
	 * create a ssl connection
	 * @return
	 */
	public CloseableHttpClient createSSLClientDefault() {
		try {
			javax.net.ssl.SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				// trust all
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

}
