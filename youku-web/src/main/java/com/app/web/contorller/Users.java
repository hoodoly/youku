package com.app.web.contorller;

import com.app.user.module.User;
import com.app.user.service.UserReadService;
import com.app.web.dto.SessionDto;
import com.app.web.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 16/9/21
 */

@Controller
@RequestMapping(value = "/api/user")
public class Users {

    @Autowired
    UserReadService userReadService;

    @Autowired
    SessionUtil sessionUtil;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam(value = "phone") String phone,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "target", required = false) String target,
                         HttpServletRequest request, HttpServletResponse response){

        User user = userReadService.findUserByParam(phone, password);
        if (user==null){
            return "";
        }
        SessionDto session = new SessionDto();
        session.setPhone(phone);
        session.setSessionId(request.getSession().getId());
        sessionUtil.putSession(session);
        if (target!=null){
            return target;
        }else {
            return "http://localhost:8080";
        }
    }
}
