package fr.fdu.cachedemo;

import fr.fdu.cachedemo.application.EhCacheEventListener;
import fr.fdu.cachedemo.domain.Game;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

    @Value("${app.cache.ttl}")
    private java.time.Duration ttl;

    @Bean
    public JCacheManagerCustomizer customizer() {
        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(new EhCacheEventListener(), EventType.CREATED, EventType.EXPIRED);

        return cm -> {
            var gameCacheConfig = CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(
                            String.class, Game.class,
                            ResourcePoolsBuilder.heap(10).offheap(10, MemoryUnit.MB))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(ttl))
                    .withService(cacheEventListenerConfiguration)
                    .build();

            cm.createCache("games", Eh107Configuration.fromEhcacheCacheConfiguration(gameCacheConfig));
        };
    }

}
