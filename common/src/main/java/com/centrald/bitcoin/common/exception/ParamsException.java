package com.centrald.bitcoin.common.exception;

/**
 * Created by central on 2016/10/27.
 */
public class ParamsException extends BitcoinException {
    public ParamsException(String message){super("参数错误",message);}
}
