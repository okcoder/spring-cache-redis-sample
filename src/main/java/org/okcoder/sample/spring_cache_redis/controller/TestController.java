package org.okcoder.sample.spring_cache_redis.controller;

import org.okcoder.sample.spring_cache_redis.servicd.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    private TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @GetMapping("short/{id}")
    public TestService.Value shortCachedDate(@PathVariable String id) {
        TestService.Value value = service.shortCachedDate(id);
        logger.info("{} now:{}",value, LocalDateTime.now());
        return  value;
    }

    @GetMapping("long/{id}")
    public TestService.Value longCachedDate(@PathVariable String id) {
        TestService.Value value = service.longCachedDate(id);
        logger.info("{} now:{}",value, LocalDateTime.now());
        return  value;
    }
}
