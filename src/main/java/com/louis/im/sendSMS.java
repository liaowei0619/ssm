package com.louis.im;

import com.louis.config.MYJZConfig;
import com.louis.exception.CustomException;
import com.louis.utilTools.MathUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
public class sendSMS {

	public static HttpResponse getSmsCode(String phone) {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/sms/sendcode.action";
		HttpPost httpPost = new HttpPost(url);

		String appKey = MYJZConfig.APPKEY;
		String appSecret = MYJZConfig.APPSECRET;
		String nonce = "" + MathUtils.getSixNum() + "";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder
				.getCheckSum(appSecret, nonce, curTime);// 参考 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", phone));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 执行请求
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String getSmsTemplateCode(String[] phones, String[] content,
			int templateid) {

		String appKey = MYJZConfig.APPKEY;
		String appSecret = MYJZConfig.APPSECRET;
		String nonce = "12345";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder
				.getCheckSum(appSecret, nonce, curTime);// 参考 计算CheckSum的java代码

		System.out.println("curTime: " + curTime);

		System.out.println("checkSum: " + checkSum);

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			String url = "https://api.netease.im/sms/sendtemplate.action";

			String params = "templateid=" + templateid + "&mobiles=[\"";
			if (phones.length > 1) {
				for (int i = 0; i < phones.length; i++) {
					if (i == (phones.length - 1)) {
						params += phones[i];
					} else {
						params += phones[i] + "\"," + "\"";
					}
				}
				params += "\"]";
			} else if (phones.length == 1) {
				params += phones[0] + "]";
			} else {
				throw new CustomException("请选取需要接收短信的用户");
			}
			if (content.length > 1) {
				params += "&params=[\"";
				for (int i = 0; i < content.length; i++) {
					if (i == (content.length - 1)) {
						params += content[i];
					} else {
						params += content[i] + "\"," + "\"";
					}
				}
				params += "\"]";
			} else if (content.length == 1) {
				params += "&params=" + "[\"" + content[0] + "\"]";
			}

			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("AppKey", appKey);
			conn.setRequestProperty("CheckSum", checkSum);
			conn.setRequestProperty("CurTime", curTime);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");
			conn.setRequestProperty("Nonce", nonce);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！\n" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		int templateid = 3062195;
		List<String> list = new ArrayList<String>();
		list.add("18328435094");
		list.add("13550528122");
		list.add("18328435095");
		String[] str = (String[]) list.toArray(new String[] {});
		List<String> list1 = new ArrayList<String>();
		list1.add("222");
		list1.add("333");
		String[] str1 = (String[]) list1.toArray(new String[] {});
		System.out.println(getSmsTemplateCode(str, str1, templateid));
		/*
		 * String code = JSONObject.fromObject(EntityUtils.toString(entity))
		 * .get("obj").toString(); System.out.println(code);
		 */
	}
}