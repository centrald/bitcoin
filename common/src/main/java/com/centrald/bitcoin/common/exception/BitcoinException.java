package com.centrald.bitcoin.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by central on 2016/10/26.
 */
public class BitcoinException extends RuntimeException {
    protected String name;

    public BitcoinException(){
        super("系统错误");
        name = "错误";
    }
    public BitcoinException(String message){
        super(message);
        name = "错误";
    }
    public BitcoinException(String name, String message){
        super(message);
        this.name = name;
    }

    @Override
    public String toString() {
        return StringUtils.isNotEmpty(getMessage()) ? name + ": " + getMessage() : name;
    }
}
