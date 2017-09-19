package com.louis.vo;

import java.util.List;

/**
 * Created by liuw on 16/11/18.
 * <p>
 * 分页查询返回 模板
 * 放在ResponseTemplate的 data属性
 */
public class BasicPageVO<T> {

    /**
     * 第几页
     */
    private int pageNum;

    /**
     * 页面个数
     */
    private int pageSize;

    /**
     * 总共个数
     */
    private int total;

    /**
     * 数据
     */
    private List<T> items;

    public BasicPageVO()
    {
        super();
    }

    public BasicPageVO(int pageNum, int pageSize, int total, List<T> items) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.items = items;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
