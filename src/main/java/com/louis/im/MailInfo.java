package com.louis.im;

import java.io.File;

public class MailInfo {
	// TLS SSL安全校验
	private boolean enabledTLS;
	private boolean enabledSSL;
	// debug跟踪
	private boolean enabledDebug;
	// 你邮箱的主机
	private String host;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 发送方，可以多个
	private String from;
	// 收件人
	private String to;
	// 编码
	private String chartSet;
	// 主题
	private String subject;
	// 简单邮件内容
	private String message;
	// stmp端口
	private int smtpPort;
	// ssl安全端口
	private String sslPort;
	// 图片url
	private String url;
	// 文件
	private File file;
	// 附件路径
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public MailInfo() {
	}

	// 简单邮件构造
	public MailInfo(String userName, String password, String from, String to,
			String host, String chartSet, String subject, String message,
			boolean enabledTLS, boolean enabledSSL, boolean enabledDebug,
			int smtpPort, String sslPort) {
		this.userName = userName;
		this.password = password;
		this.from = from;
		this.to = to;
		this.host = host;
		this.chartSet = chartSet;
		this.subject = subject;
		this.message = message;
		this.enabledTLS = enabledTLS;
		this.enabledSSL = enabledSSL;
		this.enabledDebug = enabledDebug;
		this.smtpPort = smtpPort;
		this.sslPort = sslPort;
	}

	public MailInfo(String userName, String password, String from, String to,
			String host, String chartSet, String subject, String message,
			boolean enabledTLS, boolean enabledSSL, boolean enabledDebug,
			int smtpPort, String sslPort, String url, File file) {
		this(userName, password, from, to, host, chartSet, subject, message,
				enabledTLS, enabledSSL, enabledDebug, smtpPort, sslPort);
		this.url = url;
		this.file = file;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSslPort() {
		return sslPort;
	}

	public void setSslPort(String sslPort) {
		this.sslPort = sslPort;
	}

	public boolean isEnabledTLS() {
		return enabledTLS;
	}

	public void setEnabledTLS(boolean enabledTLS) {
		this.enabledTLS = enabledTLS;
	}

	public boolean isEnabledSSL() {
		return enabledSSL;
	}

	public void setEnabledSSL(boolean enabledSSL) {
		this.enabledSSL = enabledSSL;
	}

	public boolean isEnabledDebug() {
		return enabledDebug;
	}

	public void setEnabledDebug(boolean enabledDebug) {
		this.enabledDebug = enabledDebug;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getChartSet() {
		return chartSet;
	}

	public void setChartSet(String chartSet) {
		this.chartSet = chartSet;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}