package com.centrald.bitcoin.common.util;

import com.centrald.bitcoin.common.enums.ResponseEnum;

/**
 * Created by Jason on 16/8/5.
 */
public class Result extends JsonUtils {
    protected Boolean success;
    protected Integer code;
    protected Object data;
    protected String  msg;

    public Result(ResponseEnum responseEnum) {
        success = responseEnum.getSuccess();
        code = responseEnum.getCode();
        msg = responseEnum.getMessage();
        data = "";
    }

    public Result(ResponseEnum responseEnum, Object data) {
        success = responseEnum.getSuccess();
        code = responseEnum.getCode();
        msg = responseEnum.getMessage();
        this.data = data;
    }

    public Result() {}

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJSON() {
        return toJSONString(this);
    }

    public static String success() {
        return new Result(ResponseEnum.SUCCESS).toJSON();
    }

    public static String success(Object data) {
        return new Result(ResponseEnum.SUCCESS, data).toJSON();
    }

    public static String failure() {
        return new Result(ResponseEnum.FAILURE).toJSON();
    }

    public static String failure(String rtMessage) {
        Result rtVal = new Result();
        rtVal.code = 1002;
        rtVal.success = false;
        rtVal.msg = rtMessage;
        rtVal.data = "";
        return rtVal.toJSON();
    }

    public static String respond(ResponseEnum responseEnum) {
        return new Result(responseEnum).toJSON();
    }
}
