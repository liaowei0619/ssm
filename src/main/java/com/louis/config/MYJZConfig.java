package com.louis.config;

public class MYJZConfig {

	/**
	 * api
	 */
	public static final String API = "api";
	/**
	 * ，默认密码
	 */
	public static final String PASSWORD = "123456";
	/**
	 * md5密钥
	 */
	public static final String MD5_SECRECT = "$apr1$salon";
	/**
	 * 随机用户名
	 */
	public static final int RANDOMALPHANUMERIC = 10;
	/**
	 * 资源文件bean加载名称
	 */
	public static final String RESOURCE_PROPERTYIES = "resourceProperties";
	/**
	 * url 路径
	 */
	public static final String URL_PATH = "url_path";
	/**
	 * 存放图片表格本地目录
	 */
	public static final String PIC_LOCATION = "/files/pic/";
	/**
	 * 存放file表格本地目录
	 */
	public static final String FILE_LOCATION = "/files/upload/";
	/**
	 * 存放excel表格本地目录
	 */
	public static final String EXCEL_LOCATION = "/files/excel/";

	public static final class ExcelTemplate {
		public static String FILE_PATH = "/excelTemplate/";
		public static String SHEET_PREX = "表";
		public static String FILE_TYPE = "xlsx";
		public static String TEST_TEMPLATE = "testTemplate.xlsx";
	}

	/**
	 * 短信配置文件
	 */

	public static final String APPKEY = "d14df61696c57ef7d1d7fdb9e50631d1";
	public static final String APPSECRET = "c45c8c34c31e";

	/**
	 * 邮箱配置文件
	 */
	public static final String EMAIL_USERNAME = "18328435094@163.com";
	public static final String EMAIL_PWD = "liaowei19930619";
	public static final String EMAIL_HOST = "smtp.163.com";
	public static final int EMAIL_SMTPPORT = 25;
	public static final String EMAIL_SSLPORT = "110";
	public static final String EMAIL_EMAILADRESS = "18328435094@163.com";

}
