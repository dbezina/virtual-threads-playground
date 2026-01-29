package org.bezina.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger  log = LoggerFactory.getLogger(Task.class);

    public static void toIntensive(int i)  {
        try {
            log.info("starting IO task {}.Thread info: {}", i,Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10));
            log.info("ending IO task {}.Thread info: {}", i,Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
