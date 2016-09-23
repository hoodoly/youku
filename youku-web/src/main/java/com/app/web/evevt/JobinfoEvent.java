package com.app.web.evevt;

import com.app.jobinfo.module.Jobinfo;
import lombok.Data;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 2016/9/23
 */
@Data
public class JobinfoEvent {
    private Jobinfo jobinfo;

    public JobinfoEvent(Jobinfo jobinfo){
        this.jobinfo = jobinfo;
    }
}
