package fr.fdu.cachedemo.application;

import lombok.extern.slf4j.Slf4j;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;

@Slf4j
public class CacheEventListener implements CacheEntryCreatedListener<Object, Object>, CacheEntryExpiredListener<Object, Object> {

    @Override
    public void onCreated(Iterable<CacheEntryEvent<?, ?>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent<?, ?> entryEvent : cacheEntryEvents) {
            log.info("Created entry : {} with value : {}", entryEvent.getKey(), entryEvent.getValue());
        }
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<?, ?>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent<?, ?> entryEvent : cacheEntryEvents) {
            log.info("Expired entry : {} with value : {}", entryEvent.getKey(), entryEvent.getValue());
        }
    }
}
