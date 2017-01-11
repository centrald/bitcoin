package com.centrald.bitcoin.common.enums;

/**
 * Created by Jason on 16/8/5.
 */
public enum ResponseEnum {
    SUCCESS(true, 1001, "操作成功"),
    FAILURE(false, 1002, "操作失败"),
    NULL_DATA(false, 1003, "数据为空"),
    PERMISSION_DENIED(false, 1004, "权限不足");

    private Boolean success;
    private Integer code;
    private String  message;

    ResponseEnum(Boolean success, Integer code, String message) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
