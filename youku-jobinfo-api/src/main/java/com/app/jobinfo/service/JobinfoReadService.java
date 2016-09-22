package com.app.jobinfo.service;

import com.app.jobinfo.module.Jobinfo;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/11
 */
public interface JobinfoReadService {


    List<Jobinfo> getJobinfosByParam(Map<String, Object> param);

    Jobinfo getJobinfoById(Long jobinfoId);


}
