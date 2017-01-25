package com.centrald.bitcoin.common.domain.query;



import com.centrald.bitcoin.common.util.PaginationParams;

import java.util.Date;
import java.util.List;

/**
 * Created by central on 2016/11/25.
 */
public class BaseQuery {

    protected List<Long> idList;
    protected Date created;
    protected Date updated;
    protected Boolean isDeleted = false;

    protected String creator;
    protected String creatorNick;
    protected Date createdStart;
    protected Date createdEnd;

    protected Date updatedStart;
    protected Date updatedEnd;
    protected Integer page;
    protected Integer pageSize = 9999;

    protected Integer limitStart;
    protected String  orderBy;
    protected String orderType;
    protected String keywords;

    protected List<String> snList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorNick() {return creatorNick;}

    public void setCreatorNick(String creatorNick) {this.creatorNick = creatorNick;}

    public Date getCreatedStart() {
        return createdStart;
    }

    public void setCreatedStart(Date createdStart) {
        this.createdStart = createdStart;
    }

    public Date getCreatedEnd() {
        return createdEnd;
    }

    public void setCreatedEnd(Date createdEnd) {
        this.createdEnd = createdEnd;
    }

    public Date getUpdatedStart() {
        return updatedStart;
    }

    public void setUpdatedStart(Date updatedStart) {
        this.updatedStart = updatedStart;
    }

    public Date getUpdatedEnd() {
        return updatedEnd;
    }

    public void setUpdatedEnd(Date updatedEnd) {
        this.updatedEnd = updatedEnd;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
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

    public List<String> getSnList() {
        return snList;
    }

    public void setSnList(List<String> snList) {
        this.snList = snList;
    }

    public void setPaginationParams(PaginationParams paginationParams) {
        this.page = paginationParams.getPage();
        this.pageSize = paginationParams.getPageSize();
        this.limitStart = (this.page-1) * this.pageSize;
        this.orderBy = paginationParams.getOrderBy();
        this.orderType = paginationParams.getOrderType();
        this.keywords = paginationParams.getKeywords();
    }
}
