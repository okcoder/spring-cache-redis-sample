package org.okcoder.sample.spring_cache_redis.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import java.time.Duration;

@Configuration
public class CacheConfig {

    private ObjectMapper redisObjectMapper() {
        var redisObjectMapper = new ObjectMapper();
        redisObjectMapper.registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        redisObjectMapper.activateDefaultTyping(redisObjectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.EVERYTHING);//https://github.com/FasterXML/jackson-databind/issues/3512
        return redisObjectMapper;
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(CacheProperties properties) {
        var serializer = new GenericJackson2JsonRedisSerializer(redisObjectMapper());
        var cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(SerializationPair.fromSerializer(serializer));
        return (builder) -> {
            properties.getTtlInSeconds().forEach((cacheName, seconds) ->
                    builder.withCacheConfiguration(cacheName, cacheConfig.entryTtl(Duration.ofSeconds(seconds)))
            );
        };
    }

}
