package org.bezina.sec08;

import org.bezina.utils.CommonUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Lec01SimpleCompletableFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    static void main() {
        log.info("main begin");
     //   var cf = fastTask();
        //  log.info(cf.join()); // blocking method
        var cf = slowTask();
        cf.thenAccept((v)->log.info("value {}", v)); //no blocking, but we couldn't see the value

        log.info("main ends");
    }

    private static CompletableFuture<String> fastTask(){
        log.info("fastTask begins");
        var cf = new CompletableFuture<String>();
        cf.complete("hello");
        log.info("fastTask ends");
        return cf;
    }

    private static CompletableFuture<String> slowTask(){
        log.info("slowTask begins");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(3));
            cf.complete("hi");
        });
        log.info("slowTask ends");
        return cf;
    }
}
