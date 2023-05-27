package org.okcoder.sample.spring_cache_redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "org.okcoder.sample.spring-cache-redis.cache")
public class CacheProperties {

    private Map<String,Integer> ttlInSeconds;

    public Map<String, Integer> getTtlInSeconds() {
        return ttlInSeconds;
    }

    public void setTtlInSeconds(Map<String, Integer> ttlInSeconds) {
        this.ttlInSeconds = ttlInSeconds;
    }
}
