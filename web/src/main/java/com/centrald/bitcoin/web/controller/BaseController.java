package com.centrald.bitcoin.web.controller;


import com.centrald.bitcoin.common.exception.BitcoinException;
import com.centrald.bitcoin.common.exception.ParamsException;
import com.centrald.bitcoin.common.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jiayan on 2016/10/27.
 */
public  abstract class BaseController {
    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    protected HttpServletRequest request;

    /**
     * 分页查询
     * @return
     **/
    protected PaginationParams getPaginationParams() {
        PaginationParams paginationParams = new PaginationParams();
        paginationParams.setPage(NumberUtils.toInt(request.getParameter("page"), paginationParams.getPage()));
        paginationParams.setPageSize(NumberUtils.toInt(request.getParameter("pageSize"), paginationParams.getPageSize()));
        paginationParams.setOrderBy(StringUtils.defaultIfEmpty(request.getParameter("orderBy"), null));
        paginationParams.setOrderType(StringUtils.defaultIfEmpty(request.getParameter("orderType"), null));
        paginationParams.setKeywords(StringUtils.defaultIfEmpty(request.getParameter("keywords"), null));
        return paginationParams;
    }

    /**
     * 参数 check
     * @param expression
     * @param message
     **/
    protected static void checkParam(boolean expression, String message) {
        if(!expression) {
            throw new ParamsException(message);
        }
    }
    protected static void checkParamNotEmpty(String str, String paramName) {
        checkParam(StringUtils.isNotEmpty(str), "参数" + paramName + "不能为空");
    }

    protected static void checkParamNotEmpty(List lst, String paramName) {
        checkParam(CollectionUtils.isNotEmpty(lst), "参数" + paramName + "不能为空");
    }

    protected static void checkParamNotEmpty(Object obj, String paramName) {
        checkParam(obj != null, "参数" + paramName + "不能为空");
    }
    //中文检测
    protected static void checkChinese(String str,String message) {
        if (str.getBytes().length!=str.length()) {
            throw new ParamsException(message+":有非法字符,必须为英文字符串");
        }
    }
    //ip检测
    protected static void checkIp(String ip) {
        String message = IpCheckUtils.ipCheck(ip);

        if(message!=null) {
            throw new ParamsException(message);
        }
    }
    //手机号码检测
    protected static void checkPhoneNumber(String ip) {
        Boolean message = PhoneNumberCheckUtils.isPhoneLegal(ip);

        if(!message) {
            throw new ParamsException("请输入合法手机号码");
        }
    }


    @ExceptionHandler
    @ResponseBody
    public String exceptionHandler(Exception e) throws Exception {
        logger.error(e.toString());
        if (e instanceof BitcoinException) {
            return Result.failure(e.toString());
        } else if (e instanceof MissingServletRequestParameterException) {
            return Result.failure(new ParamsException("参数" + ((MissingServletRequestParameterException) e).getParameterName() + "不能为空").toString());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return Result.failure(new ParamsException("参数" + ((MethodArgumentTypeMismatchException) e).getName() + "类型错误").toString());
        } else {
            if (!PropertyUtils.getString("env").equals("dev") && !PropertyUtils.getString("env").equals("local")) {
                if ("1".equals(request.getParameter("error"))) {
                    throw e;
                } else {
                    e.printStackTrace();
                    return Result.failure(new BitcoinException().toString());
                }
            } else {
                throw e;
            }
        }
    }


}
