package com.centrald.bitcoin.web.controller;

import com.centrald.bitcoin.common.util.RSAUtils;
import com.centrald.bitcoin.common.util.UUIDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by central on 2017/1/10.
 */
@Controller
@RequestMapping(path = "/user", produces = "application/json; charset=utf-8")
public class UserController extends BaseController {

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam String phoneNumber, @RequestParam String password) {

        checkParamNotEmpty(phoneNumber,"phoneNumber");
        checkParamNotEmpty(password,"password");
        checkPhoneNumber(phoneNumber);

        //根据phoneNumber查询用户

        //生成唯一标识符
        String uuid = UUIDUtils.getUUID();

        //加密密码
    }


}
