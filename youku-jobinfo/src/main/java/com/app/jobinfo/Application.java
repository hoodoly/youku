package com.app.jobinfo;

import com.app.user.module.User;
import com.app.user.service.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/12
 */
@Slf4j
public class Application {


    public static void main(String[] args) throws InterruptedException {
        final ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("spring/youku-jobinfo-dubbo-consumer.xml");
        ac.start();
        UserReadService userReadService = (UserReadService) ac.getBean("userReadService");
        User user = userReadService.findUserById();
        log.info(user.getName());
        log.info("Jobinfo Service started successfully");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Shutdown hook was invoked. Shutting down Jobinfo Service.");
                ac.close();
            }
        });
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }
}
