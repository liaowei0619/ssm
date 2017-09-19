package com.louis.utilTools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by liuw on 16/11/23.
 * <p>
 * resource.properties 资源文件加载类
 */
public class ResourceUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(ResourceUtils.class);
	private static Properties props;
	static {
		loadProps();
	}
	synchronized static private void loadProps() {
		logger.info("开始加载properties文件内容.......");
		props = new Properties();
		InputStream in = null;
		try {
			// 第一种 通过类加载器进行获取properties文件流
			in = ResourceUtils.class.getClassLoader().getResourceAsStream(
					"resource.properties");
			// 　 第二种，通过类进行获取properties文件流-
			// in = ResourceUtils.class.getResourceAsStream("/jdbc.properties");
			props.load(in);
		} catch (FileNotFoundException e) {
			logger.error("jdbc.properties文件未找到");
		} catch (IOException e) {
			logger.error("出现IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("jdbc.properties文件流关闭出现异常");
			}
		}
		logger.info("properties文件内容：" + props);
	}
	public static String getProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key);
	}
	public static String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}
	public static void main(String[] args) {
		System.out.println(ResourceUtils.getProperty("url_path"));
	}
}
