package com.example.imdb.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	public SimpleCacheManager cacheManager() {
		var manager = new SimpleCacheManager();
		Collection<Cache> caches = new ArrayList<>();
		caches.add(new ConcurrentMapCache("genres"));
		manager.setCaches(caches);
		return manager;
	}
}
