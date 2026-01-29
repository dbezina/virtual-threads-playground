package org.bezina.sec08;


import org.bezina.sec08.aggregator.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Lec06AllOf {
    private static final Logger log = LoggerFactory.getLogger(Lec06AllOf.class);

    static void main() {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        var completableFutures = IntStream.rangeClosed(1, 100)
                                .mapToObj((id)->
                                    CompletableFuture.supplyAsync(()-> aggregator.getProductDto(id)
                                    ,executor))
                                .toList();
        //wait all completable futures to complete
        CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new)).join();

        var list = completableFutures.stream()
                .map(CompletableFuture::join)
                .toList()
                ;

        log.info("list {}",list);

    }
}
