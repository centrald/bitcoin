package com.centrald.bitcoin.common.util;

import java.util.List;

/**
 * Created by Jason on 16/8/5.
 */
public class Pagination<T> {

    private List<T> list;           //数据
    private Integer count;          //当前页数据条数
    private Integer totalCount;     //总条数
    private Integer page;           //页码
    private Integer totalPage;      //总页数
    private Integer pageSize;       //页数
    private String  orderBy;        //排序依据
    private String  orderType;      //排序类型
    private String  keywords;       //搜索关键字

    public Pagination(List<T> list, Integer totalCount, PaginationParams paginationParams) {
        this.list = list;
        this.count = list.size();
        this.totalCount = totalCount;
        this.page = paginationParams.getPage();
        this.totalPage = paginationParams.getPageSize() != 0 ? (int) Math.ceil((double) this.totalCount / paginationParams.getPageSize()) : 0;
        this.pageSize = paginationParams.getPageSize();
        this.orderBy = paginationParams.getOrderBy();
        this.orderType = paginationParams.getOrderType();
        this.keywords = paginationParams.getKeywords();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
