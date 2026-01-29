package org.bezina.sec08;

import org.bezina.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec02RunAsync {
    private static final Logger log = LoggerFactory.getLogger(Lec02RunAsync.class);

    static void main() {
        log.info("main begin");
        runAsync()
                .thenRun(()->log.info("it is done")) //non blocking
                .exceptionally( (e) ->{
                    log.error("error {}",e.getMessage());
                    return null;
                })
        ;
        log.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(3));
    }


    private static CompletableFuture<Void> runAsync(){
        log.info("runAsync begins");
        var cf = CompletableFuture.runAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(2));
            throw new RuntimeException("smth went wrong");
         //   log.info("runAsync completed");
        }, Executors.newVirtualThreadPerTaskExecutor()); // VT will be used instead if ForkJoinPool
        log.info("runAsync ends");
        return cf;
    }
}
