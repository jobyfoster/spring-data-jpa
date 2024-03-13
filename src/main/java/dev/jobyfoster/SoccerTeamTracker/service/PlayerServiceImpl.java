package dev.jobyfoster.SoccerTeamTracker.service;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.PlayerRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Player;
import dev.jobyfoster.SoccerTeamTracker.model.Team;
import dev.jobyfoster.SoccerTeamTracker.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    @Override
    public Player addPlayer(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        player.setName(playerRequestDto.getName());
        Team team = teamService.getTeam(playerRequestDto.getTeam().getId());
        player.setTeam(team);
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    @Override
    public Player getPlayer(Long playerId) {
        return playerRepository.findById(playerId).orElseThrow(
                () -> new IllegalArgumentException("Player with playerId " + playerId + " does not exist")
        );
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player player = getPlayer(playerId);
        playerRepository.delete(player);
    }

    @Override
    public Player editPlayer(Long playerId, PlayerRequestDto playerRequestDto) {
        Player playerToEdit = getPlayer(playerId);
        playerToEdit.setName(playerRequestDto.getName());
        if(playerRequestDto.getTeam() != null) {
            Team team = teamService.getTeam(playerRequestDto.getTeam().getId());
            playerToEdit.setTeam(team);
        }
        return playerRepository.save(playerToEdit);
    }
}
