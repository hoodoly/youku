package com.app.web.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 2016/9/23
 */
@Component
@Slf4j
public class ActivityJob {

    public void testJob(){
        log.info("job start");
    }
}
