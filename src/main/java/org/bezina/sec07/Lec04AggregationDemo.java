package org.bezina.sec07;

import org.bezina.sec07.aggregator.AggregatorService;
import org.bezina.sec07.aggregator.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Lec04AggregationDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec04AggregationDemo.class);

    static void main() throws InterruptedException, ExecutionException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);
        var futures = IntStream.rangeClosed(1, 10)
                .mapToObj((id)->
                    executor.submit(()->aggregator.getProductDto(id))
                )
                .toList();
//        var futures = IntStream.rangeClosed(1, 50)
//                .mapToObj(id -> executor.submit(() -> aggregator.getProductDto(id))).toList();
        var list = futures.stream()
                .map(Lec04AggregationDemo::toProductDto)
                .toList()
                ;
       log.info("list {}",list);
        // beans / singletons
       // var executor = Executors.newVirtualThreadPerTaskExecutor();
      //  var aggregator = new AggregatorService(executor);


//        var list1 = futures.stream()
//                .map(Lec04AggregationDemo::toProductDto)
//                .toList();
//
//        log.info("list: {}", list1);

    }

    private static ProductDto toProductDto(Future<ProductDto> future){
        try {
            return future.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

 }
