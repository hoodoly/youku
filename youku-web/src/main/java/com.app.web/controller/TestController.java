package com.app.web.controller;


import com.app.jobinfo.module.Jobinfo;
import com.app.jobinfo.service.JobinfoReadService;
import com.app.search.service.SearchJobinfoReadService;
import com.app.search.service.SearchJobinfoWriteService;
import com.app.user.module.User;
import com.app.user.service.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/14
 */
@Controller
@Slf4j
public class TestController {

    @Autowired
    UserReadService userReadService;

    @Autowired
    SearchJobinfoWriteService searchJobinfoWriteService;

    @Autowired
    SearchJobinfoReadService searchJobinfoReadService;

    @Autowired
    JobinfoReadService jobinfoReadService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public User findUser(){
        User user = userReadService.findUserById();
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Jobinfo> getJobinfos(@RequestParam(required = false) String keyWord){
        searchJobinfoWriteService.indicesJobInfo();

        List<Jobinfo> jobinfos = searchJobinfoReadService.search(keyWord);
        return jobinfos;
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseBody
    public Jobinfo getJobinfo(@RequestParam(required = false) Long id){

        Jobinfo jobinfo = jobinfoReadService.getJobinfoById(id);
        log.info(jobinfo.getId().toString());
        return jobinfo;
    }
}
