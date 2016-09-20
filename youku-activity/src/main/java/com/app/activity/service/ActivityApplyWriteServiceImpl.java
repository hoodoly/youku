package com.app.activity.service;

import com.app.activity.dao.ActivityApplyDao;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/20
 */
@Service
@Slf4j
public class ActivityApplyWriteServiceImpl implements ActivityApplyWriteService {

    @Autowired
    ActivityApplyDao activityApplyDao;

    public Boolean applyActivity(Long activityId, String phone) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("activityId", 1);
        param.put("phone", phone);
        param.put("result", -3);
        activityApplyDao.applyActivityProcedure(param);
        if (MapUtils.getIntValue(param, "result", -2) < 0){
            log.info("apply.failed");
            return false;
        }else if (MapUtils.getIntValue(param, "result", -2)==0){
            log.info("already.apply");
            return false;
        }else if (MapUtils.getIntValue(param, "result", -2)==1){
            return true;
        }
        return false;
    }
}
