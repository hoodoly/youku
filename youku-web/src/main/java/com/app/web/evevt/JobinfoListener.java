package com.app.web.evevt;

import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.BlockingArrayQueue;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 2016/9/23
 */
@Component
@Slf4j
public class JobinfoListener {


    private ExecutorService executorService;

    public JobinfoListener(){
        this.executorService = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MINUTES,
                new BlockingArrayQueue<Runnable>(100),
                new ThreadFactoryBuilder().build(),
                new RejectedExecutionHandler(){

                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                        TestTask testTask =  (TestTask) runnable;
                    }
                });
    }

    @Subscribe
    public void onTest(JobinfoEvent event){
        log.info("event invoke" + event.getJobinfo().getJobName());
    }

    private class TestTask implements Runnable{

        public void run() {
        }
    }
}
