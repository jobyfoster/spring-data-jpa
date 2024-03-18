package dev.jobyfoster.SoccerTeamTracker.service;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.TeamRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Player;
import dev.jobyfoster.SoccerTeamTracker.model.Team;
import dev.jobyfoster.SoccerTeamTracker.repository.PlayerRepository;
import dev.jobyfoster.SoccerTeamTracker.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Team addTeam(TeamRequestDto teamRequestDto) {
        Team team = new Team();
        team.setCoaches(teamRequestDto.getCoaches());
        team.setName(teamRequestDto.getName());
        team.setPlayers(teamRequestDto.getPlayers());
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    @Override
    public Team getTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(
                () -> new IllegalArgumentException("Team with teamId " + teamId + " could not be found")
        );
    }

    @Override
    public void deleteTeam(Long teamId) {
        Team team = getTeam(teamId);
        teamRepository.delete(team);
    }

    @Transactional
    @Override
    public Team editTeam(Long teamId, TeamRequestDto teamRequestDto) {
        Team teamToEdit = getTeam(teamId);
        teamToEdit.setPlayers(teamRequestDto.getPlayers());
        teamToEdit.setName(teamRequestDto.getName());
        teamToEdit.setCoaches(teamRequestDto.getCoaches());

        return teamRepository.save(teamToEdit);
    }

    @Override
    @Transactional
    public Team updateTeamPlayers(Long teamId, List<Long> playerIds) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team with ID " + teamId + " not found"));

        // Clear existing players to avoid constraint violations
        team.getPlayers().clear();
        teamRepository.save(team); // Save immediately to update the relationship

        // Fetch and add new players
        List<Player> updatedPlayers = (List<Player>) playerRepository.findAllById(playerIds);
        for (Player player : updatedPlayers) {
            team.addPlayer(player);
            // If you're managing the relationship in Player too, set it here
            player.setTeam(team);
        }

        return teamRepository.save(team);
    }

}
