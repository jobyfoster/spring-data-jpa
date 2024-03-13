package dev.jobyfoster.SoccerTeamTracker.service;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.TeamRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {

    Team addTeam(TeamRequestDto teamRequestDto);

    List<Team> getTeams();

    Team getTeam(Long teamId);

    void deleteTeam(Long teamId);

    Team editTeam(Long teamId, TeamRequestDto teamRequestDto);
}
