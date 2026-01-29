package org.bezina.sec08;

import org.bezina.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec03SupplyAsync {
    private static final Logger log = LoggerFactory.getLogger(Lec03SupplyAsync.class);

    static void main() {
        log.info("main begin");
     //   var cf = fastTask();
        //  log.info(cf.join()); // blocking method
        var cf = slowTask();
        cf.thenAccept((v)->log.info("value {}", v)); //no blocking, but we couldn't see the value

        log.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(3));

    }

    private static CompletableFuture<String> slowTask(){
        log.info("slowTask begins");
        var cf =  CompletableFuture.supplyAsync(()->{
                    CommonUtils.sleep(Duration.ofSeconds(2));
                    return "hi";
                }, Executors.newVirtualThreadPerTaskExecutor());

        log.info("slowTask ends");
        return cf;
    }
}
