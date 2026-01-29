package org.bezina.sec07;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class.getName());
    private static final String PRODUCT_REQUEST_FORMAT = "http://localhost:7070/sec01/product/%d";
    private static final String RATING_REQUEST_FORMAT = "http://localhost:7070/sec01/rating/%d";

    public static String getProduct(int id){
        return callExternalService( String.format(PRODUCT_REQUEST_FORMAT, id));
    }
    public static Integer getRating(int id){
        return Integer.parseInt(callExternalService(String.format(RATING_REQUEST_FORMAT, id)));
    }

    private static String callExternalService(String url){
        logger.info("call url {}", url);
        try (var stream = URI.create(url).toURL().openStream();)
        {
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
