package com.louis.utilTools;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UtilsTools {
	// 编码转换
	public static String encodingTool(String str) {
		try {
			System.out.println(str.getBytes());
			return new String(str.getBytes(UtilsTools.getEncoding(str)),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GB2312
				String s = encode;
				return s; // 是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return ""; // 如果都不是，说明输入的内容不属于常见的编码格式。
	}

	/**
	 * 
	 * @param param1
	 *            :总的字符串
	 * @param param2
	 *            ：分隔符
	 * @param param3
	 *            ：需要移除的字符串
	 * @return
	 */
	public static String romoveStr(String param1, String param2, String param3) {
		String result = "";
		String[] removes = param1.split(param2);
		for (String remove : removes) {
			if (!remove.equalsIgnoreCase(param3)
					&& StringUtils.isNotEmpty(remove)) {
				result = result + remove + ",";
			}

		}
		return result;

	}

	/**
	 * 获取所有的Id
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static List<String> getIDs(String param1, String param2) {
		List<String> ids = new ArrayList<>();
		String[] removes = param1.split(param2);
		for (int i = 0; i < removes.length; i++) {
			if (StringUtils.isNotEmpty(removes[i])) {
				ids.add(removes[i]);
			}
		}

		return ids;

	}

	
	public static void main(String[] args) {
		System.out.println(getIDs(",,2018,,,2012,,2014,2015", ","));
		
		System.out.println("20170202011".compareToIgnoreCase("20170202012"));
	}
}
