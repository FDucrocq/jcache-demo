package fr.fdu.cachedemo.application;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.impl.events.CacheEventAdapter;

@Slf4j
public class EhCacheEventListener extends CacheEventAdapter<Object, Object> {

    @Override
    protected void onCreation(Object key, Object newValue) {
        log.info("Created [{}]=[{}]", key, newValue);
    }

    @Override
    protected void onRemoval(Object key, Object removedValue) {
        log.info("Removed [{}]=[{}]", key, removedValue);
    }

    @Override
    protected void onExpiry(Object key, Object expiredValue) {
        log.info("Expired [{}]=[{}]", key, expiredValue);
    }

    @Override
    protected void onEviction(Object key, Object evictedValue) {
        log.info("Evicted [{}]=[{}]", key, evictedValue);
    }
}
