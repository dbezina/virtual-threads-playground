package org.bezina.sec03;

import org.bezina.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static Logger logger = LoggerFactory.getLogger(Task.class);

    public static void cpuIntensive(int i) {
        logger.info("start cpuIntensive. Thread info: {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(()-> Task.findFib(i));
        logger.info("end cpuIntensive. Time taken: {} ms", timeTaken);
    }

    public static long findFib(long input) {
        if (input < 2) {return input;}
        return findFib(input-1) + findFib(input-2);
    }
}
