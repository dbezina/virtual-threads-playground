package org.bezina.sec08;

import org.bezina.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Lec08ThenCombine {
    private static Logger log = LoggerFactory.getLogger(Lec08ThenCombine.class);
    record Airfare(String airline, double amount){};

    public static void main(String[] args) {

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            var cf1 = getDeltaAirfare(executor);
            var cf2 = getFrontierAirfare(executor);
            var bestDeal = cf1.thenCombine(cf2, (a, b) -> a.amount <= b.amount ? a : b)
                    .thenApply(air -> new Airfare(air.airline, air.amount * 0.9)) //+ discount
                    .join();
            log.info("best deal={}", bestDeal);
          //  log.info("airfare={}", CompletableFuture.anyOf(cf1, cf2).join());
        }

    }

    private static CompletableFuture<Airfare> getDeltaAirfare(ExecutorService executor){
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtils.sleep(Duration.ofMillis(random));
            log.info("Delta={}", random);
            return new Airfare("Delta" , random);
        }, executor);
    }

    private static CompletableFuture<Airfare> getFrontierAirfare(ExecutorService executor){
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtils.sleep(Duration.ofMillis(random));
            log.info("Frontier={}", random);
            return new Airfare("Frontier" , random);
        }, executor);
    }

}
