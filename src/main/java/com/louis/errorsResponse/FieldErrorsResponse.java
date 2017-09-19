package com.louis.errorsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuw on 16/11/8.
 * <p>
 * FieldErrorsResponse include message code and message while a wrong http request coming
 * <p>
 * it can used by Http POST Method
 */
public class FieldErrorsResponse {

    /**
     * whether is successful
     */
    private boolean isSuccess;

    /**
     * message code
     */
    private int code;

    /**
     * message message
     */
    private List<String> fieldErrors;

    /**
     * request path
     */
    private String path;

    /**
     * default constructor
     */
    public FieldErrorsResponse() {

    }

    /**
     * constructor with message code
     *
     * @param code message code
     */
    public FieldErrorsResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * add a error message to fieldErrors
     *
     * @param error message
     */
    public void addError(String error) {
        if (null == fieldErrors) {
            fieldErrors = new ArrayList<String>();
        }
        fieldErrors.add(error);
    }
}
