package dev.jobyfoster.SoccerTeamTracker.controller;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.PlayerRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Player;
import dev.jobyfoster.SoccerTeamTracker.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerService.addPlayer(playerRequestDto);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Player player = playerService.getPlayer(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestDto playerRequestDto) {
        Player updatedPlayer = playerService.editPlayer(id, playerRequestDto);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }
}
