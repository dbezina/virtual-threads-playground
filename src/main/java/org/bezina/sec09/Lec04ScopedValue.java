package org.bezina.sec09;

import org.bezina.sec09.controller.DocumentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec04ScopedValue {
    private static final Logger log = LoggerFactory.getLogger(Lec04ScopedValue.class);
    private static final ScopedValue<String> SESSION_TOKEN = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(SESSION_TOKEN, "session1")
                .run(()->checkBinding()); // the value is set and removed automaticaly
        ;
        checkBinding();
    }

    private static void checkBinding(){
        log.info("is bound ? : {}", SESSION_TOKEN.isBound());
       // log.info("value ? {}", SESSION_TOKEN.get()); // will generate Exception
        log.info("value :{}", SESSION_TOKEN.orElse("default token"));
    }
}
