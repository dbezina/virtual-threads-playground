package org.bezina.sec03;

import org.bezina.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;


public class CPUTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(CPUTaskDemo.class);

    private static final int TASKS_COUNT = 3*Runtime.getRuntime().availableProcessors();
  //private static final int TASKS_COUNT = 5 ;

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(
//                CommonUtils.timer(()-> Task.findFib(28))
//        );
      //demo(Thread.ofPlatform());
        demo(Thread.ofVirtual());
    }
    private static void demo(Thread.Builder builder) throws InterruptedException {
        var latch = new CountDownLatch(TASKS_COUNT);
        for (int i = 1; i <= TASKS_COUNT; i++) {
            builder.start(()->{
                    Task.cpuIntensive(45);
                    latch.countDown();
            });
        }
            latch.await();

    }

}
