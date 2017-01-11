package com.centrald.bitcoin.common.domain.entity;

/**
 * Created by central on 2017/1/10.
 */
public class UserInfo extends BaseEntity{

    private String phoneNumber;
    private String userName;
    private String password;
    private String uuid;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {return uuid;}

    public void setUuid(String uuid) {this.uuid = uuid;}
}
