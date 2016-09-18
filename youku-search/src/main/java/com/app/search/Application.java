package com.app.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/17
 */
@Slf4j
public class Application {

    public static void main(String[] args) throws InterruptedException {
        final ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        ac.start();
        log.info("Search Service started successfully");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Shutdown hook was invoked. Shutting down Search Service.");
                ac.close();
            }
        });
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }
}
