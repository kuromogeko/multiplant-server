package kurmogeko.multiplant.infrastructure.repo;

import kurmogeko.multiplant.domain.entities.Session;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SizeCacheConfiguration {

    @Bean
    public Cache<String, Session> sizeLimitedSessionCache() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        CacheConfigurationBuilder<String, Session> sessionCacheConfigurationBuilder = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Session.class, ResourcePoolsBuilder.heap(19).build());
        return cacheManager
                .createCache("sessionCache", sessionCacheConfigurationBuilder);
    }

}
