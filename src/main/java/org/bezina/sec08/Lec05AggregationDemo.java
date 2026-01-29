package org.bezina.sec08;

import org.bezina.sec08.aggregator.AggregatorService;
import org.bezina.sec08.aggregator.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Lec05AggregationDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec05AggregationDemo.class);

    static void main() throws Exception {

        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        log.info("product1 = {}",aggregator.getProductDto(-1));

    }

    private static ProductDto toProductDto(Future<ProductDto> future){
        try {
            return future.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

 }
