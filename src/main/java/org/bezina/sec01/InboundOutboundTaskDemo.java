package org.bezina.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 20;

    static void main() throws InterruptedException {

     //  platformThreadDemo1();
        virtualThreadDemo1();

    }

    private static void platformThreadDemo1() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.toIntensive(j));
            thread.start();
        }

    }

    private static void platformThreadDemo2() {
        Thread.Builder.OfPlatform  builder = Thread.ofPlatform().name("T-",1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.toIntensive(j));
            thread.start();
        }
    }

    private static void platformThreadDemo3() throws InterruptedException {
        var countDownLatch  = new CountDownLatch(MAX_PLATFORM);
        Thread.Builder.OfPlatform  builder = Thread.ofPlatform().daemon().name("Daemon-",1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.toIntensive(j);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }
    private static void virtualThreadDemo1() throws InterruptedException {
        var countDownLatch  = new CountDownLatch(MAX_VIRTUAL);

        Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("Virtual-",1);
        for (int i = 0; i < MAX_VIRTUAL; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.toIntensive(j);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }
}
