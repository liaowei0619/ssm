package com.louis.errorsResponse;

/**
 * Created by liuw on 16/11/8.
 * ErrorResponse
 */
public class ErrorResponse {
    /**
     * whether is successful
     */
    private boolean isSuccess;

    /**
     * error code
     */
    private int code;

    /**
     * error message
     */
    private String message;

    /**
     * request path
     */
    private String path;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(int code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path=path;
        this.isSuccess=false;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSuccess() { return isSuccess; }

    public void setSuccess(boolean success) { isSuccess = success; }
}
