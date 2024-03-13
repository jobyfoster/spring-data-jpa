package dev.jobyfoster.SoccerTeamTracker.dto;

import dev.jobyfoster.SoccerTeamTracker.dto.responseDto.CoachResponseDto;
import dev.jobyfoster.SoccerTeamTracker.dto.responseDto.PlayerResponseDto;
import dev.jobyfoster.SoccerTeamTracker.dto.responseDto.TeamResponseDto;
import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import dev.jobyfoster.SoccerTeamTracker.model.Player;
import dev.jobyfoster.SoccerTeamTracker.model.Team;

import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static TeamResponseDto teamToTeamResponseDto(Team team){
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getId());
        teamResponseDto.setName(team.getName());
        teamResponseDto.setCoaches(team.getCoaches());
        List<String> names = new ArrayList<>();
        List<Player> players = team.getPlayers();
        for (Player player: players){
            names.add(player.getName());
        }
        teamResponseDto.setPlayerNames(names);
        return teamResponseDto;
    }

    public static List<TeamResponseDto> teamsToTeamsResponseDtos(List<Team> teams){
        List<TeamResponseDto> teamResponseDtos = new ArrayList<>();

        for (Team team: teams){
            teamResponseDtos.add(teamToTeamResponseDto(team));
        }

        return teamResponseDtos;
    }

    public static CoachResponseDto coachToCoachResponseDto(Coach coach) {
        CoachResponseDto coachResponseDto = new CoachResponseDto();
        coachResponseDto.setId(coach.getId());
        coachResponseDto.setName(coach.getName());
        coachResponseDto.setTeams(coach.getTeams());

        return coachResponseDto;
    }

    public static List<CoachResponseDto> coachesToCoachesResponseDtos(List<Coach> coaches){
        List<CoachResponseDto> coachResponseDtos = new ArrayList<>();

        for (Coach coach: coaches){
            coachResponseDtos.add(coachToCoachResponseDto(coach));
        }

        return coachResponseDtos;
    }

    public static PlayerResponseDto playerToPlayerResponseDto(Player player) {
        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(player.getId());
        playerResponseDto.setName(player.getName());
        playerResponseDto.setTeam(player.getTeam());

        return playerResponseDto;
    }

    public static List<PlayerResponseDto> playersToPlayersResponseDtos(List<Player> players){
        List<PlayerResponseDto> playerResponseDtos = new ArrayList<>();

        for (Player player: players){
            playerResponseDtos.add(playerToPlayerResponseDto(player));
        }

        return playerResponseDtos;
    }

}
