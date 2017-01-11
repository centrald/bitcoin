package com.centrald.bitcoin.common.util;


import com.centrald.bitcoin.common.enums.ResponseEnum;

import java.util.List;

/**
 * Created by ijayan on 16/6/12.
 */
public class PaginationResult extends Result {

    private Integer count;          //当前页数据条数
    private Integer totalCount;     //总条数
    private Integer page;           //页码
    private Integer totalPage;      //总页数
    private Integer pageSize;       //页数
    private String orderBy;         //排序依据
    private String orderType;       //排序类型
    private String keywords;        //搜索关键字

    public PaginationResult(ResponseEnum responseEnum, Pagination pageResult) {
        super(responseEnum, pageResult.getList());
        count = pageResult.getCount();
        totalCount = pageResult.getTotalCount();
        page = pageResult.getPage();
        totalPage = pageResult.getTotalPage();
        pageSize = pageResult.getPageSize();
        orderBy = pageResult.getOrderBy();
        orderType = pageResult.getOrderType();
        keywords = pageResult.getKeywords();
    }

    public static String respond(ResponseEnum responseEnum, Pagination pagination) {
        PaginationResult result = new PaginationResult(responseEnum, pagination);

        return result.toJSON();
    }

    public static String success(Pagination pagination) {
        return respond(ResponseEnum.SUCCESS, pagination);
    }

    public static <T> String success(List<T> list, Integer totalCount, PaginationParams paginationParams) {
        Pagination pagination = new Pagination<>(list, totalCount, paginationParams);

        return success(pagination);
    }

    public PaginationResult() {
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
