package fr.fdu.cachedemo.adapter.game;

import fr.fdu.cachedemo.domain.Game;
import fr.fdu.cachedemo.domain.api.GamePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class StaticGameAdapter implements GamePort {

    private static final List<Game> games = new ArrayList<>();

    static {
        games.add(new Game("GTFO", "10 Chambers", Instant.parse("2021-12-10T03:31:19Z")));
        games.add(new Game("Frostpunk", "11 bit studios", Instant.parse("2018-04-24T00:00:00Z")));
        games.add(new Game("Valheim", "Iron Gate Studio", Instant.parse("2021-02-02T00:00:00Z")));
    }

    @Override
    public List<Game> all() {
        return games;
    }

    @Override
    public Optional<Game> findByTitle(String title) {
        try {
            log.info("Contacting fake external service...");
            Thread.sleep(Long.parseLong("5000"));
        } catch (InterruptedException e) {
            log.error("Error while simulating waiting time!", e);
            throw new RuntimeException(e);
        }

        return games.stream()
                .filter(g -> g.getTitle().equals(title))
                .findFirst();
    }

}
