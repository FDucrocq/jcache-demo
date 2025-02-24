package fr.fdu.cachedemo.domain.api;

import fr.fdu.cachedemo.domain.Game;

import java.util.List;
import java.util.Optional;

public interface GamePort {

    List<Game> all();

    Optional<Game> findByTitle(String title);

}
