package org.okcoder.sample.spring_cache_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheRedisSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheRedisSampleApplication.class, args);
	}

}
