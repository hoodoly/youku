package com.app.search.service;

import com.app.jobinfo.module.Jobinfo;

import java.util.List;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/12
 */
public interface SearchJobinfoReadService {
    /**
     * seacch jobinfo list
     * @return
     */
    List<Jobinfo> search(String keyWord);

}
