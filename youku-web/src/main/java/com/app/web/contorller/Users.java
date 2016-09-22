package com.app.web.contorller;

import com.app.user.module.User;
import com.app.user.service.UserReadService;
import com.app.web.dto.SessionDto;
import com.app.web.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/login")
    @ResponseBody
    public Boolean login(@RequestParam(value = "phone") String phone, @RequestParam(value = "password") String password,
                         HttpServletRequest request, HttpServletResponse response){


        User user = userReadService.findUserByParam(phone, password);
        if (user==null){
            return false;
        }
        SessionDto session = new SessionDto();
        session.setPhone(phone);
        session.setSessionId(request.getSession().getId());

        sessionUtil.putSession(session);
        return true;
    }
}
