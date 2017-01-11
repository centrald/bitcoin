package com.centrald.bitcoin.common.util;

import com.centrald.bitcoin.common.exception.ParamsException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author jiayan
 */
public class PaginationParams {

    private Integer page        = 1;    //页码
    private Integer pageSize    = 20;   //每页数量
    private String orderBy      = "id";             //排序依据
    private String orderType    = "DESC";           //排序类型
    private String keywords;            //搜索关键字

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page < 1) page = 1;
        this.page = page;
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
        if (StringUtils.isEmpty(orderType) || "ASC".equalsIgnoreCase(orderType) || "DESC".equalsIgnoreCase(orderType)) {
            this.orderType = orderType;
        } else {
            throw new ParamsException("orderType只能为空, ASC或DESC");
        }
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
