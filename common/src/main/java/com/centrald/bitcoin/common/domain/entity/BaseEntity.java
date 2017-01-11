package com.centrald.bitcoin.common.domain.entity;

import com.centrald.bitcoin.common.domain.constant.BitcoinConstant;

import java.util.Date;

public abstract class BaseEntity {

    protected Long      id;
    protected Date      created;
    protected Date      updated;
    protected Boolean   isDeleted       = false;
    protected String    creator         = BitcoinConstant.SYSTEM_USERNAME;
    protected String    creatorNick     = BitcoinConstant.SYSTEM_USERNICK;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorNick() {
        return creatorNick;
    }

    public void setCreatorNick(String creatorNick) {
        this.creatorNick = creatorNick;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", isDeleted=" + isDeleted +
                ", creator='" + creator + '\'' +
                ", creatorNick='" + creatorNick + '\'' +
                '}';
    }
}
