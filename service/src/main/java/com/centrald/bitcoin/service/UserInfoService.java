package com.centrald.bitcoin.service;

import com.centrald.bitcoin.common.domain.entity.UserInfo;
import com.centrald.bitcoin.common.domain.query.UserInfoQuery;
import com.centrald.bitcoin.dao.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by central on 2017/1/11.
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public void update(UserInfo userInfo) { userInfoMapper.update(userInfo);}

    public List<UserInfo> query(UserInfoQuery userInfoQuery) {
        return userInfoMapper.query(userInfoQuery);
    }

    public UserInfo get(Long id) {
        return userInfoMapper.get(id);
    }

    public void delete(Long id) {userInfoMapper.delete(id);}

    public void insert(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }
}
