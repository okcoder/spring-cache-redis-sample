package org.okcoder.sample.spring_cache_redis.servicd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestService {

    public record Value(String id,LocalDateTime latestTime){};
    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Cacheable(cacheNames = "long", key = "#id")
    public Value longCachedDate(String id) {
        Value value = new Value(id,LocalDateTime.now());
        logger.info("longCachedDate: {}",value);
        return value;
    }

    @Cacheable(cacheNames = "short", key = "#id")
    public Value shortCachedDate(String id) {
        Value value = new Value(id,LocalDateTime.now());
        logger.info("longCachedDate: {}",value);
        return value;
    }
}
