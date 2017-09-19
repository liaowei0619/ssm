package com.louis.vo;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by liuw on 16/11/26.
 */
public class BasicPageRequestVO<T> {

    /**
     * 页码
     */
    @Min(value = 1,message = "{pageNum.content.illegal}")
    private int pageNum = 1;

    /**
     * 页面个数
     */
    @Min(value = 0,message = "{pageSize.content.illegal}")
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String order = "createdDate";

    /**
     * desc 降序 asc 升序
     */
    private String sort = "desc";

    @Valid
    private T conditions;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public T getConditions() {
        return conditions;
    }

    public void setConditions(T conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "BasicPageRequestVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", order='" + order + '\'' +
                ", sort='" + sort + '\'' +
                ", conditions=" + conditions +
                '}';
    }
}
