package org.bezina.sec08;

import org.bezina.sec07.Client;
import org.bezina.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lec04GetProducts {
    private static final Logger log = LoggerFactory.getLogger(Lec04GetProducts.class.getName());

    static void main() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
          var product1 = CompletableFuture.supplyAsync(() -> Client.getProduct(1),executor);
          var product2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2),executor);
          var product3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3),executor);

          log.info("product-1 {}", product1.get());
          log.info("product-2 {}", product2.get());
          log.info("product-3 {}", product3.get());

        }
//        log.info("main begin");
//        //   var cf = fastTask();
//        //  log.info(cf.join()); // blocking method
//        var cf = getProduct(1);
//        cf.thenAccept((v)->log.info("value {}", v)); //no blocking, but we couldn't see the value
//
//        log.info("main ends");
//        CommonUtils.sleep(Duration.ofSeconds(3));
    }

    private static CompletableFuture<String> getProduct( int id){
        log.info("getProduct for id {} begins",id);
        var cf =  CompletableFuture.supplyAsync(()-> Client.getProduct(id)
                , Executors.newVirtualThreadPerTaskExecutor());

        log.info("getProduct for id {} ends",id);
        return cf;
    }
}
