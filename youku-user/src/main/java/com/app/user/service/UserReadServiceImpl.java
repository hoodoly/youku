package com.app.user.service;

import com.app.user.dao.UserDao;
import com.app.user.module.User;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/8
 */
@Service
public class UserReadServiceImpl implements UserReadService {

    @Autowired
    UserDao userDao;

    public User findUserById(Long userId) {
        User user = userDao.load(userId);
        return user;
    }

    public User findUserByParam(String phone, String password) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("phone", phone);
        param.put("password", password);
        List<User> users = userDao.queryByParams(param);
        if (users==null || users.isEmpty()){
            return null;
        }
        User user = users.get(0);
        return user;
    }

    public List<User> loads(List<Long> ids) {
        return userDao.loads(ids);
    }


}
