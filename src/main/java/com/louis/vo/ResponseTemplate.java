package com.louis.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * created by@author liaowei on 2017年1月9日
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTemplate {

	/**
	 * whether is successful
	 */
	private boolean isSuccess;

	/**
	 * tips for system
	 */
	private String message;

	/**
	 * response data
	 */
	private Object data;

	/**
	 * default constructor
	 */
	public ResponseTemplate() {
	}

	/**
	 * constructor with data, set isSuccess as true
	 * 
	 * @param data
	 *            response data
	 */
	public ResponseTemplate(Object data) {
		this.isSuccess = true;
		this.data = data;
	}

	/**
	 * constructor with message, set isSuccess as false
	 * 
	 * @param message
	 *            error tips
	 */
	public ResponseTemplate(String message) {
		this.isSuccess = false;
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
