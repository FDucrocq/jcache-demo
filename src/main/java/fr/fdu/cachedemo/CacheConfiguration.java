package fr.fdu.cachedemo;

import fr.fdu.cachedemo.application.CacheEventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfiguration {

    @Value("${app.cache.ttl}")
    private java.time.Duration ttl;

    @Bean
    public JCacheManagerCustomizer customizer(MutableConfiguration<Object, Object> configuration) {
        return cacheManager -> cacheManager.createCache("games", configuration);
    }

    @Bean
    public MutableConfiguration<Object, Object> configuration(
            CacheEntryListenerConfiguration<Object, Object> listenerConfiguration) {
        MutableConfiguration<Object, Object> configuration = new MutableConfiguration<>();
        configuration.addCacheEntryListenerConfiguration(listenerConfiguration);
        configuration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(
                new Duration(TimeUnit.MILLISECONDS, ttl.toMillis()))
        );

        return configuration;
    }

    @Bean
    public CacheEntryListenerConfiguration<Object, Object> listenerConfiguration() {
        return new MutableCacheEntryListenerConfiguration<>(FactoryBuilder.factoryOf(CacheEventListener.class),
                null,
                false,
                true);
    }

}
