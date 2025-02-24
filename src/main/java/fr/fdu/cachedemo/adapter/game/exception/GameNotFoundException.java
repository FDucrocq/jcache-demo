package fr.fdu.cachedemo.adapter.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No game found")
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String message) {
        super(message);
    }

}
