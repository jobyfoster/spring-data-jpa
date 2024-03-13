package dev.jobyfoster.SoccerTeamTracker.service;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.PlayerRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Player;

import java.util.List;

public interface PlayerService {
    Player addPlayer(PlayerRequestDto playerRequestDto);
    List<Player> getPlayers();
    Player getPlayer(Long playerId);
    void deletePlayer(Long playerId);
    Player editPlayer(Long playerId, PlayerRequestDto playerRequestDto);
}

