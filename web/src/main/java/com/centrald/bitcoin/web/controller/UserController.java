package com.centrald.bitcoin.web.controller;

import com.centrald.bitcoin.common.domain.entity.UserInfo;
import com.centrald.bitcoin.common.domain.query.UserInfoQuery;
import com.centrald.bitcoin.common.enums.UserStatusEnum;
import com.centrald.bitcoin.common.util.Base64Utils;
import com.centrald.bitcoin.common.util.Result;
import com.centrald.bitcoin.common.util.UUIDUtils;
import com.centrald.bitcoin.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by central on 2017/1/10.
 */
@Controller
@RequestMapping(path = "/user", produces = "application/json; charset=utf-8")
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登出
     */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(@RequestParam String phoneNumber) {

        checkParamNotEmpty(phoneNumber,"phoneNumber");


        UserInfoQuery userInfoQuery = new UserInfoQuery();

        userInfoQuery.setPhoneNumber(phoneNumber);

        List<UserInfo> userInfoList = userInfoService.query(userInfoQuery);

        if(CollectionUtils.isEmpty(userInfoList)) {
            return Result.failure("该用户没有登录");
        }

        for (int i=0;i<userInfoList.size();i++) {
            // TODO: 2017/1/12 删除该用户session

            UserInfo userInfo = userInfoList.get(0);
            userInfo.setStatus(UserStatusEnum.OFFLINE.name());

            userInfoService.update(userInfo);
        }

        return Result.success();
    }

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam String phoneNumber, @RequestParam String password) {

        checkParamNotEmpty(phoneNumber,"phoneNumber");
        checkParamNotEmpty(password,"password");

        checkPhoneNumber(phoneNumber);

        //根据phoneNumber查询用户
        UserInfoQuery userInfoQuery = new UserInfoQuery();

        //加密密码
        password = Base64Utils.getBASE64(password);

        userInfoQuery.setPassword(password);
        userInfoQuery.setPhoneNumber(phoneNumber);

        List<UserInfo> userInfoList = userInfoService.query(userInfoQuery);

        if(CollectionUtils.isEmpty(userInfoList)) {
            return Result.failure("手机号码或密码不正确!");
        }

        if (userInfoList.size()>1) {
            return Result.failure("有多个重复用户!");
        }

        for (int i=0;i<userInfoList.size();i++) {

            UserInfo userInfo = userInfoList.get(0);

            // TODO: 2017/1/12 创建session 

            userInfo.setStatus(UserStatusEnum.ONLINE.name());

            userInfoService.update(userInfo);
        }

        return Result.success();
    }

    /**
     * 注册
     * @param phoneNumber
     * @param password
     * @return
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam String phoneNumber, @RequestParam String password,
                           @RequestParam String email) {

        checkParamNotEmpty(phoneNumber, "phoneNumber");
        checkParamNotEmpty(password, "password");
        checkParamNotEmpty(email, "email");

        checkPhoneNumber(phoneNumber);
        checkEmail(email);

        //根据phoneNumber查询用户
        UserInfoQuery userInfoQuery = new UserInfoQuery();

        userInfoQuery.setPhoneNumber(phoneNumber);

        List<UserInfo> userInfoList = userInfoService.query(userInfoQuery);

        if(CollectionUtils.isNotEmpty(userInfoList)) {
            return Result.failure("该手机号码已经注册!");
        }

        //生成唯一标识符
        String uuid = UUIDUtils.getUUID();

        //加密密码
        password = Base64Utils.getBASE64(password);

        UserInfo userInfo = new UserInfo();

        userInfo.setPhoneNumber(phoneNumber);
        userInfo.setPassword(password);
        userInfo.setUuid(uuid);
        userInfo.setStatus(UserStatusEnum.OFFLINE.name());

        userInfo.setEmail(email);

        userInfoService.insert(userInfo);

        return Result.success();
    }


}
