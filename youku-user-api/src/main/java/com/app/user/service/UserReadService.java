package com.app.user.service;


import com.app.user.module.User;

import java.util.List;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/8
 */
public interface UserReadService {

    User findUserById(Long userId);

    User findUserByParam(String phone, String password);

    List<User> loads(List<Long> ids);
}
