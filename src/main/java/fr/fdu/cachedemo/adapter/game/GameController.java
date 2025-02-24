package fr.fdu.cachedemo.adapter.game;

import fr.fdu.cachedemo.application.GameUseCase;
import fr.fdu.cachedemo.domain.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GameController.BASE_API)
@RequiredArgsConstructor
public class GameController {

    protected static final String BASE_API = "/api/games";

    private final GameUseCase useCase;

    @GetMapping
    public ResponseEntity<List<Game>> all() {
        List<Game> games = useCase.all();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Game> byTitle(@PathVariable String title) {
        Game game = useCase.byTitle(title);
        return ResponseEntity.ok(game);
    }

}
