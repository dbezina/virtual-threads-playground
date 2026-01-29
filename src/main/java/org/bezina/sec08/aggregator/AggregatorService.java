package org.bezina.sec08.aggregator;

import org.bezina.sec07.Client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class AggregatorService {
    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {

        this.executorService = executorService;
    }

    public ProductDto getProductDto(int id) {
        var product = CompletableFuture.supplyAsync(() -> Client.getProduct(id), executorService)
                .exceptionally(ex -> "product not found") ;
        var rating =  CompletableFuture.supplyAsync(() -> Client.getRating(id), executorService)
                .exceptionally(ex -> -1)
                .orTimeout(1750, TimeUnit.MILLISECONDS)
                .exceptionally(ex -> -2) // for timeout
                ;
       // return new ProductDto(id, product.get(), rating.get());
        return new ProductDto(id, product.join(), rating.join());
    }
}
