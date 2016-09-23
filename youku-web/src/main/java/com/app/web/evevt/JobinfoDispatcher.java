package com.app.web.evevt;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 2016/9/23
 */
@Component
public class JobinfoDispatcher {

    private final EventBus eventBus;

    @Autowired
    private ApplicationContext applicationContext;

    public JobinfoDispatcher(){
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(5));

    }

    @PostConstruct
    public void registerLiseners(){
        JobinfoListener jobinfoListener = (JobinfoListener) applicationContext.getBean("jobinfoListener");
        eventBus.register(jobinfoListener);
    }

    public void publich(JobinfoEvent event){
        eventBus.post(event);
    }
}
