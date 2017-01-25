package com.centrald.bitcoin.dao.mapper;

import com.centrald.bitcoin.common.domain.entity.UserInfo;
import com.centrald.bitcoin.common.domain.query.UserInfoQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by central on 2017/1/11.
 */
@Repository
public interface UserInfoMapper {

    UserInfo get(Long id);

    int insert(UserInfo userInfo);

    List<UserInfo> query(UserInfoQuery userInfoQuery);

    int delete(Long id);

    void update(UserInfo userInfo);
}
