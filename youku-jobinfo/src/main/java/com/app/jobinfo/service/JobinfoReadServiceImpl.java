package com.app.jobinfo.service;

import com.app.user.module.User;
import com.app.user.service.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/11
 */
@Slf4j
@Service
public class JobinfoReadServiceImpl implements JobinfoReadService {

    @Autowired
    private UserReadService userReadService;

    @Override
    public User getUser() {
        User user = userReadService.findUserById();
        log.info(user.getName());
        return user;
    }
}
