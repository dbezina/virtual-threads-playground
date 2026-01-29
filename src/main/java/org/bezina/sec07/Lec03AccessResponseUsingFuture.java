package org.bezina.sec07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lec03AccessResponseUsingFuture {
    private static final Logger logger = LoggerFactory.getLogger(Lec03AccessResponseUsingFuture.class.getName());

    static void main() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
          Future<String> future = executor.submit(() -> Client.getProduct(1));
          logger.info("product-1 {}",future.get());
          Future<String> future2 = executor.submit(() -> Client.getProduct(2));
          logger.info("product-2 {}",future.get());
          Future<String> future3 = executor.submit(() -> String.valueOf(Client.getRating(2)));
          logger.info("rating-3 {}",future3.get());
        }
    }
}
