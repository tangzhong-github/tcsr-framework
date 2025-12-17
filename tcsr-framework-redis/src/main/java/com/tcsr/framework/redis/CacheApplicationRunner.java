package com.tcsr.framework.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tangzhong
 * @date   2025-11-04 9:04
 * @since  V1.0.0
 */
@Slf4j
@Component
public class CacheApplicationRunner implements ApplicationRunner {

    @Autowired
    private List<CacheHandler> cacheHandlers;

    @Override
    public void run(ApplicationArguments args){
        log.info("Start to load caches at {}", LocalDateTime.now());
        StopWatch stopWatch = new StopWatch("Load caches");
        cacheHandlers.forEach(handler->{
            stopWatch.start(String.format("Load cache for [%s]", handler.toString()));
            handler.cache();
            stopWatch.stop();
        });
        log.info(stopWatch.prettyPrint());
    }

}