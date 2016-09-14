package com.app.user.service;

import com.app.user.module.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/8
 */
@Service
public class UserReadServiceImpl implements UserReadService {

    public User findUserById() {
        User user = new User();
        user.setName("tom");
        return user;
    }

    public List<User> findUsersByIds() {
        return null;
    }
}
