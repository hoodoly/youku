package com.app.web.contorller;

import com.app.activity.service.ActivityApplyWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/20
 */
@Controller
public class ActivityApply {

    @Autowired
    ActivityApplyWriteService activityApplyWriteService;

    @RequestMapping(value = "/api/apply", method = RequestMethod.GET)
    @ResponseBody
    public Boolean applyActivity(@RequestParam String phone){
        Boolean isSuccess = activityApplyWriteService.applyActivity(null, phone);
        return isSuccess;
    }
}
