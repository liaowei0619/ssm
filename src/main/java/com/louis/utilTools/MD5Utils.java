package com.louis.utilTools;

import com.louis.config.MYJZConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * created by @author liaowei on 2017年1月9日
 * 
 * md5加密
 */
public class MD5Utils {

	private MD5Utils() {
	}

	/**
	 * md5 encode
	 *
	 * @param key
	 * @return
	 */
	public static String encode(String key) {
		return Md5Crypt.apr1Crypt(key, MYJZConfig.MD5_SECRECT);
	}

	/**
	 * 小文件文件md5校验
	 *
	 * @param file
	 * @return
	 */
	public static String encode(File file) {
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return DigestUtils.md5Hex(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
