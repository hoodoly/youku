package com.app.jobinfo.service;

import com.app.jobinfo.dao.JobinfoDao;
import com.app.jobinfo.dao.rediesDao.JobinfoRedisDao;
import com.app.jobinfo.module.Jobinfo;
import com.app.user.module.User;
import com.app.user.service.UserReadService;
import io.terminus.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Autowired
    JobinfoDao jobinfoDao;

    @Autowired
    JobinfoRedisDao jobinfoRedisDao;

    @Override
    public User getUser() {
        User user = userReadService.findUserById();
        log.info(user.getName());
        return user;
    }

    @Override
    public List<Jobinfo> getJobinfosByParam(Map<String, Object> param) {
        return jobinfoDao.list(param);
    }

    @Override
    public Jobinfo getJobinfoById(Long jobinfoId) {
        Jobinfo jobinfo = jobinfoRedisDao.get(jobinfoId);
        if (jobinfo == null){
            jobinfo = jobinfoDao.load(jobinfoId);
            if (jobinfo!=null){
                jobinfoRedisDao.put(jobinfo);
            }else {
                log.error("jobinfo:"+jobinfoId + "不存在");
                throw new ServiceException();
            }
        }
        return jobinfo;
    }
}
