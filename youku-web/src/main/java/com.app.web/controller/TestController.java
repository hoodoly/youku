package com.app.web.controller;


import com.app.user.module.User;
import com.app.user.service.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/14
 */
@Controller
@Slf4j
@RequestMapping(value = "/api")
public class TestController {

    @Autowired
    UserReadService userReadService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public User findUser(@RequestParam String userName){
        User user = userReadService.findUserById();
        return user;
    }
}
