package com.app.web.contorller;


import com.app.jobinfo.module.Jobinfo;
import com.app.jobinfo.service.JobinfoReadService;
import com.app.search.service.SearchJobinfoReadService;
import com.app.search.service.SearchJobinfoWriteService;
import com.app.user.module.User;
import com.app.user.service.UserReadService;
import com.app.web.dto.SessionDto;
import com.app.web.evevt.JobinfoDispatcher;
import com.app.web.evevt.JobinfoEvent;
import com.app.web.utils.SessionUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    JobinfoDispatcher jobinfoDispatcher;



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public User findUser(){
        User user = userReadService.findUserById(1L);
        List<Long> ids = Lists.newArrayList(1L, 3L);

        List<User> users = userReadService.loads(ids);
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getJobinfos(@RequestParam(required = false) String keyWord, HttpServletRequest request){
        searchJobinfoWriteService.indicesJobInfo();

        List<Jobinfo> jobinfos = searchJobinfoReadService.search(keyWord);
        String sessionId = request.getSession().getId();
        SessionDto Session = sessionUtil.getSession(request.getSession().getId());
        log.info(Session.getPhone());

        //log.info(UserUtils.getCurrentUser().getUsername());
        return "index";
    }


    @RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseBody
    public Jobinfo getJobinfo(@RequestParam(required = false) Long id){

        Jobinfo jobinfo = jobinfoReadService.getJobinfoById(id);
        JobinfoEvent event = new JobinfoEvent(jobinfo);
        jobinfoDispatcher.publich(event);
        log.info(jobinfo.getId().toString());
        return jobinfo;
    }
}
