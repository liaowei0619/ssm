package com.louis.exception;

/**
 * Created by liuw on 16/11/20.
 *
 * 自定义异常
 */
public class CustomException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * default constructor
     * @param msg 错误信息
     */
    public CustomException(String msg) {
        super(msg);
    }
}
