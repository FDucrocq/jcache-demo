package fr.fdu.cachedemo.application;

import fr.fdu.cachedemo.adapter.game.exception.GameNotFoundException;
import fr.fdu.cachedemo.domain.Game;
import fr.fdu.cachedemo.domain.api.GamePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameUseCase {

    private final GamePort gamePort;
    private final CacheManager cacheManager;

    public List<Game> all() {
        return gamePort.all();
    }

    @Cacheable(value = "games", key = "#title")
    public Game byTitle(String title) {
        Optional<Game> optGame = gamePort.findByTitle(title);
        if (optGame.isPresent()) {
            return optGame.get();
        }

        throw new GameNotFoundException(title);
    }

    @Scheduled(fixedRate = 1000)
    public void printCacheEntries() {
        JCacheCache cache = (JCacheCache) cacheManager.getCache("games");
        if (cache != null) {
            log.debug("---- CACHE ENTRIES -----");
            Cache<Object, Object> nativeCache = cache.getNativeCache();
            nativeCache.forEach(entry -> log.debug("[{}]=[{}]", entry.getKey(), entry.getValue()));
        }
    }

}

