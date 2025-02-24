package fr.fdu.cachedemo.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class Game implements Serializable {

    private String title;
    private String developers;
    private Instant realeased;

    public Game(String title, String developers, Instant realeased) {
        this.title = title;
        this.developers = developers;
        this.realeased = realeased;
    }
}
