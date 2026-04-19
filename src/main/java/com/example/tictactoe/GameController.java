package com.example.tictactoe; 

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameService getGame() {
        return gameService;
    }

    @PostMapping("/move/{index}")
    public String move(@PathVariable int index) {
        return gameService.makeMove(index);
    }

    @PostMapping("/reset")
    public void reset() {
        gameService.reset();
    }
}