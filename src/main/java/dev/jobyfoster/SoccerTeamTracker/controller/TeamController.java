package dev.jobyfoster.SoccerTeamTracker.controller;

import dev.jobyfoster.SoccerTeamTracker.dto.mapper;
import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.TeamRequestDto;
import dev.jobyfoster.SoccerTeamTracker.dto.responseDto.TeamResponseDto;
import dev.jobyfoster.SoccerTeamTracker.model.Team;
import dev.jobyfoster.SoccerTeamTracker.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamService.addTeam(teamRequestDto);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        Team team = teamService.getTeam(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody TeamRequestDto teamRequestDto) {
        Team updatedTeam = teamService.editTeam(id, teamRequestDto);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @PutMapping("/{id}/players")
    public ResponseEntity<TeamResponseDto> updateTeamPlayers(@PathVariable Long id, @RequestBody List<Long> playerIds) {
        Team team = teamService.updateTeamPlayers(id, playerIds);
        return new ResponseEntity<>(mapper.teamToTeamResponseDto(team), HttpStatus.OK);
    }

}
