package com.centrald.bitcoin.common.enums;

/**
 * Created by central on 2016/12/1.
 */
public enum UserStatusEnum {

    ONLINE("在线"),
    OFFLINE("下线");

    UserStatusEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
